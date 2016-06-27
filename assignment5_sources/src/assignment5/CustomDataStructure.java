package assignment5;


public class CustomDataStructure {
    private Character value = null;
    private CustomDataStructure Node = null;


    //Getter - Setter functions
    public void SetValue(char val){
        if(Node!= null){
            Node.SetValue(val);
        }
        else if(value!=null){
            Node = new CustomDataStructure();
            Node.SetValue(val);
        }
        else value = val;
    }

    public char GetValue(int index){
        if(index == 0) return value;
        else if(Node==null) throw new IndexOutOfBoundsException();
        else return Node.GetValue(index-1);
    }
    //Empty the Structure
    public void eraseAll(){
        value = null;
        Node = null;
    }
    
}
