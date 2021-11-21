package groovytest

class GroovyTest {
   static void main(String[] args){
       def colors = [jave: '学习', python: '不学习', c: '底层']
      println(colors.jave)
   }
    static String testGroovy(){
        def name = 'java'
        def greeting = "Hello ${name}"
        return greeting
    }

}
