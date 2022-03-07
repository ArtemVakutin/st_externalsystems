package ru.bk.artv.greet;

public class EveningGreet extends Greet{
    @Override
    public String buildResponse(String name){
        return "Good Evening " + name;
    }
}
