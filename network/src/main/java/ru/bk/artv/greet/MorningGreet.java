package ru.bk.artv.greet;

public class MorningGreet extends Greet{
    @Override
    public String buildResponse(String name){
        return "Good Morning " + name;
    }
}
