package com.taltools.utils;

import com.alibaba.excel.EasyExcel;
import com.taltools.entity.Constants;
import com.taltools.entity.KnowStatisticsEmailReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Slf4j
@Component
public class EmailUtil {

    private static Session session;
    private static final String FROM = "";
    private static final String PASSWORD = "";
    private static final String PORT = "465";
    private static final String HOST = "smtp.tal.com";
    private static final String isAUTH = "true";
    private static final String TIMEOUT = "25000";
    private static final String FILE_PATH = "excel/";

    static {
        Properties props = System.getProperties();
        props.setProperty("mail.mime.splitlongparameters", "false");
        props.setProperty("mail.smtp.host", HOST);
        props.setProperty("mail.smtp.port", PORT);
        //开启认证
        props.setProperty("mail.smtp.auth", isAUTH);
        //设置链接超时
        props.setProperty("mail.smtp.timeout", TIMEOUT);
//        props.setProperty("mail.debug", "true");//启用调试
//        props.setProperty("mail.smtp.port", "465");//设置端口
        props.setProperty("mail.smtp.socketFactory.port", "465");//设置ssl端口
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // 初始化session
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        });
    }

    public void send(String toList, String ccList, String subject, String htmlContent, File attachment)
            throws IOException, MessagingException {
        send(toList, ccList, subject, htmlContent, attachment);
    }

    /**
     * 发送带附件的邮件
     * to 接收人列表，使用";"分割
     * cc 抄送列表，使用";"分割
     * subject 邮件主题
     * htmlContent 邮件内容
     * attachmentList 邮件附件
     */
    public void send(String to, String cc, String subject, String htmlContent, List<File> attachmentList)
            throws IOException, MessagingException {

        MimeMessage message = initMimeMessage(to, cc);
        // 主题
        message.setSubject(subject);
        message.setSentDate(new Date());

        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();
        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(htmlContent, "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        // 添加附件的内容
        if (CollectionUtils.isNotEmpty(attachmentList)) {
            for (File attachment : attachmentList) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                String filename = MimeUtility.encodeWord(attachment.getName());
                filename = filename.replace("\\r", "").replace("\\n", "").replace(" ", "");
                attachmentBodyPart.setFileName(filename);
                multipart.addBodyPart(attachmentBodyPart);
            }
        }
        message.setContent(multipart);
        Transport.send(message);
    }


    /**
     * 发送带附件的邮件
     * to 接收人列表，使用";"分割
     * cc 抄送列表，使用";"分割
     * subject 邮件主题
     * htmlContent 邮件内容
     * attachmentList 邮件附件
     */
    public void send(@Valid KnowStatisticsEmailReq req) throws IOException, MessagingException {

        MimeMessage message = initMimeMessage(req.getToList(), req.getCcList());
        // 主题
        message.setSubject(req.getSubject());
        message.setSentDate(new Date());

        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();
        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(req.getContent(), "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        // 添加附件的内容
        if (CollectionUtils.isNotEmpty(req.getAttachmentList())) {
            for (int i = 0; i < req.getAttachmentList().size(); i++) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new ByteArrayDataSource(req.getAttachmentList().get(i).toByteArray(), "application/msexcel");
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                String filename = MimeUtility.encodeWord(req.getAttachmentNameList().get(i));
                filename = filename.replace("\\r", "").replace("\\n", "").replace(" ", "");
                attachmentBodyPart.setFileName(filename);
                multipart.addBodyPart(attachmentBodyPart);
            }
        }

        message.setContent(multipart);
        message.saveChanges();
        Transport.send(message);
    }

    /**
     * 初始化消息对象,填充收件人和抄送人,(发件人是默认的)
     *
     * @param toList 收件人列表
     * @param ccList 抄送人
     * @return MimeMessage
     * @throws MessagingException
     */
    private MimeMessage initMimeMessage(String toList, String ccList) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM));
        // 接收人
        String[] receivers = toList.split(Constants.SEPARATE_SEMICOLON);
        Address[] addresses = new InternetAddress[receivers.length];
        for (int i = 0; i < receivers.length; i++) {
            addresses[i] = new InternetAddress(receivers[i]);
        }
        message.addRecipients(RecipientType.TO, addresses);
        // 抄送
        if (StringUtils.isNotBlank(ccList)) {
            String[] ccs = ccList.split(Constants.SEPARATE_SEMICOLON);
            Address[] ccAddress = new InternetAddress[ccs.length];
            for (int i = 0; i < ccs.length; i++) {
                ccAddress[i] = new InternetAddress(ccs[i]);
            }
            message.addRecipients(RecipientType.CC, ccAddress);
        }
        return message;
    }

    /**
     * 将数据变成excel文件流
     *
     * @param dataList 数据列表
     * @param clazz    类，需要包含 ExcelProperty 注解
     * @param <T>      类型
     * @return 文件流
     */
    public <T> ByteArrayOutputStream dataToStream(List<T> dataList, Class<T> clazz) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        EasyExcel.write(byteArrayOutputStream, clazz).sheet("sheet1").autoTrim(Boolean.FALSE)
                .doWrite(dataList);
        return byteArrayOutputStream;

    }

    /**
     * Excel文件生成
     *
     * @param workbook
     * @param fileName
     * @throws IOException
     */
    public File createFile(Workbook workbook, String fileName) throws IOException {
        File file = new File(FILE_PATH + fileName);
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                // 新建文件目录失败，抛异常
                String msg = "创建文件(父层文件夹)失败, filepath: " + file.getAbsolutePath();
                log.error(msg);
                throw new IOException(msg);
            }
        }
        // 判断文件是否存在，不存在则创建
        if (!file.exists()) {
            if (!file.createNewFile()) {
                // 新建文件失败，抛异常
                String msg = "创建文件失败, filepath: " + file.getAbsolutePath();
                log.error(msg);
                throw new IOException(msg);
            }
        }
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        return file;
    }

}