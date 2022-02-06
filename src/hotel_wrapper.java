public class hotel_wrapper{
    private String string_name;
    private String name;
    private int value;
    public hotel_wrapper(String n, int v){
        string_name = n;
        name = n;
        value = v;
    }

    public String toString(){
        return string_name;
    }
    public int getVal(){
        return value;
    }
    public String getName(){
        return name;
    }
}