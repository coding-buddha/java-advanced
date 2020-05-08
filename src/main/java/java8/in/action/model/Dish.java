package java8.in.action.model;

public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type){
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName(){
        return this.name;
    }

    public boolean isVegetarian(){
        return this.vegetarian;
    }

    public int getCalories(){
        return this.calories;
    }

    public Type getType(){
        return this.type;
    }

    public enum Type{
        MEAT, FISH, OTHER
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegetarian=" + vegetarian +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }
}