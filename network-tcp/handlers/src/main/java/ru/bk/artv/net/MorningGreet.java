package ru.bk.artv.net;

public class MorningGreet extends Greet{
    @Override
    public String buildResponse(String name){
        return "Good Morning " + name;
    }
}
