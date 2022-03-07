package ru.bk.artv.net;

public class DayGreet extends Greet{
    @Override
    public String buildResponse(String name){
        return "Good Day, commander " + name;
    }
}
