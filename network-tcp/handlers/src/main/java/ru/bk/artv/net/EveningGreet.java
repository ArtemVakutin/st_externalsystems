package ru.bk.artv.net;

public class EveningGreet extends Greet{
    @Override
    public String buildResponse(String name){
        return "Good Evening " + name;
    }
}
