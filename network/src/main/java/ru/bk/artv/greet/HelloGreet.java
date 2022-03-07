package ru.bk.artv.greet;

public class HelloGreet extends Greet{
    @Override
    public String buildResponse(String name){
        return "Hello " + name;
    }
}
