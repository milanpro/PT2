package assignment5;


public class CustomDataStructure {
    private Character value;
    private CustomDataStructure Node;
    private int StructLength;
    public CustomDataStructure(){
        value = null;
        Node = null;
        StructLength = 1;
    }

    //Getter - Setter functions
    public void SetValue(char val){
        if(Node!= null){
            Node.SetValue(val);
        }
        else if(value!=null){
            Node = new CustomDataStructure();
            Node.SetValue(val);
            Node.SetLength(StructLength+1);
        }
        else value = val;
    }
    public void SetLength(int l) {StructLength = l;}

    public Character GetValue(int index){
        if(index == 0) return value;
        else if(Node==null) throw new IndexOutOfBoundsException();
        else return Node.GetValue(index-1);
    }

    public void SetLastNode(CustomDataStructure Last){
        if(Node!=null) Node.SetLastNode(Last);
        else Node = Last;
    }
    public void DetachNode(CustomDataStructure DeNode){
        if(Node!=null&&Node!=DeNode) Node.DetachNode(DeNode);
        else if(Node!=null&&Node==DeNode) Node=null;
        else throw new NullPointerException();
    }

    //Empty the Structure
    public void eraseAll(){
        value = null;
        Node = null;
    }

    public int length(){
        if(Node!=null){
            return Node.length();
        }
        else return StructLength;
    }
}
