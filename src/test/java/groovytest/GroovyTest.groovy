package groovytest

class GroovyTest {
   static void main(String[] args){
       println(testGroovy())
   }
    static String testGroovy(){
        def name = 'java'
        def greeting = "Hello ${name}"
        return greeting
    }

}
