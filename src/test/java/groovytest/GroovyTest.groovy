package groovytest

class GroovyTest {
   static void main(String[] args){
       def name = 'java'
       def greeting = "Hello ${name}"
       def  myVar = greeting;
       println(myVar)
   }
    static String testGroovy(){
        def name = 'java'
        def greeting = "Hello ${name}"
        return greeting
    }

}
