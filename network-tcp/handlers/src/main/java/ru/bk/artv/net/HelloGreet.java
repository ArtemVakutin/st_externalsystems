package ru.bk.artv.net;

public class HelloGreet extends Greet{
    @Override
    public String buildResponse(String name){
        return "Hello " + name;
    }
}
