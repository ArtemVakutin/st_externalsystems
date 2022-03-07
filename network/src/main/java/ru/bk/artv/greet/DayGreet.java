package ru.bk.artv.greet;

public class DayGreet extends Greet{
    @Override
    public String buildResponse(String name){
        return "Good Day, commander " + name;
    }
}
