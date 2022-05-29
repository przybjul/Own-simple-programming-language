import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

enum VarType{ INT, REAL, UNKNOWN }

class Value{
    public String name;
    public VarType type;
    public Value( String name, VarType type ){
        this.name = name;
        this.type = type;
    }
}

public class LLVMActions extends DemoBaseListener {

    HashMap<String, VarType> global_var = new HashMap<String, VarType>();
    HashMap<String, VarType> local_var = new HashMap<String, VarType>();
    HashSet<String> functions = new HashSet<String>();
    String  function;//value już jest jako klasa
    Boolean global;
    int function_type;


    {
        function_type = 0;
    }

    Stack<Value> stack = new Stack<Value>();

    //apply of assignment for local and global variables
    @Override
    public void exitAssign(DemoParser.AssignContext ctx) {
        String ID = ctx.ID().getText();
        Value v = stack.pop();
        //if function exists
        if( functions.contains(v.name) ){
        if(function_type==1){
            if(global_var.containsKey(ID)){
            LLVMGenerator.assign_i32("@" + ID, "%" + (LLVMGenerator.reg - 1));}
        else{
            global_var.put(ID, VarType.INT);
            LLVMGenerator.declare_i32(ID,global);
            LLVMGenerator.assign_i32("@" + ID, "%" + (LLVMGenerator.reg - 1));
            }}
        else if(function_type==2){
            if(global_var.containsKey(ID)){
                LLVMGenerator.assign_double("@" + ID, "%" + (LLVMGenerator.reg - 1));}
            else{
                global_var.put(ID, VarType.REAL);
                LLVMGenerator.declare_double(ID,global);
                LLVMGenerator.assign_double("@" + ID, "%" + (LLVMGenerator.reg - 1));
            }}
        else
            System.err.println("Line: "+ctx.getStart().getLine()+ " Unknown function " + v.name);}
        else{
        if(global){//outside function
        if( ! global_var.containsKey(ID) ) {
            if(global_var.containsKey(v.name)){
                global_var.put(ID, global_var.get(v.name));
                if (global_var.get(v.name) == VarType.INT) {
                    LLVMGenerator.declare_i32(ID,global);
                }
                if (global_var.get(v.name) == VarType.REAL) {
                    LLVMGenerator.declare_double(ID,global);
                }
            }
            else if(v.type == VarType.UNKNOWN)
                System.err.println("Line "+ ctx.getStart().getLine()+", unknown variable: "+v.name);
            else{
                global_var.put(ID, v.type);
            if (v.type == VarType.INT) {
                LLVMGenerator.declare_i32(ID,global);
            }
            if (v.type == VarType.REAL) {
                LLVMGenerator.declare_double(ID,global);
            }}
        }
        if(global_var.containsKey(v.name)){
            if( global_var.get(v.name) == VarType.INT ){
                LLVMGenerator.load_i32("@" + v.name);
                LLVMGenerator.assign_i32("@" + ID, "%" + (LLVMGenerator.reg - 1));
            }
            if(global_var.get(v.name) == VarType.REAL ){
                LLVMGenerator.load_double("@" + v.name);
             LLVMGenerator.assign_double("@" + ID, "%" + (LLVMGenerator.reg - 1));
        }}
        else{
            if( v.type == VarType.INT ){
                LLVMGenerator.assign_i32("@" + ID, v.name);
            }
            if( v.type == VarType.REAL ){
                LLVMGenerator.assign_double("@" + ID, v.name);
            }
        }}
        else{//inside function
        if(global_var.containsKey(ID) ){}
        else if( ! local_var.containsKey(ID) ) {
            if(local_var.containsKey(v.name)) {

                local_var.put(ID, local_var.get(v.name));
                if (local_var.get(v.name) == VarType.INT) {
                    LLVMGenerator.declare_i32(ID, global);
                }
                if (local_var.get(v.name) == VarType.REAL) {
                    LLVMGenerator.declare_double(ID, global);
                }
            }
            else if(global_var.containsKey(v.name)){
                local_var.put(ID, global_var.get(v.name));
                if ( global_var.get(v.name) == VarType.INT ) {
                    LLVMGenerator.declare_i32(ID,global);
                }
                if ( global_var.get(v.name) == VarType.REAL ) {
                    LLVMGenerator.declare_double(ID,global);
                }
            }
            else{

                local_var.put(ID, v.type);
            if (v.type == VarType.INT) {
                LLVMGenerator.declare_i32(ID,global);
            }
            if (v.type == VarType.REAL) {
                LLVMGenerator.declare_double(ID,global);
            }}
        }
        if(global_var.containsKey(ID) ){
            if(local_var.containsKey(v.name)){
                if( local_var.get(v.name) == VarType.INT ){
                    LLVMGenerator.load_i32("%" + v.name);
                    LLVMGenerator.assign_i32("@" + ID, "%" + (LLVMGenerator.reg - 1));
                }
                else if( local_var.get(v.name) == VarType.REAL ){
                    LLVMGenerator.load_double("%" + v.name);
                    LLVMGenerator.assign_double("@" + ID, "%" + (LLVMGenerator.reg - 1));
                }}
            else if(global_var.containsKey(v.name)){
                if( global_var.get(v.name) == VarType.INT ){
                    LLVMGenerator.load_i32("@" + v.name);
                    LLVMGenerator.assign_i32("@" + ID,"%" + (LLVMGenerator.reg - 1));
                }
                if( global_var.get(v.name) == VarType.REAL ){
                    LLVMGenerator.load_double("@" + v.name);
                    LLVMGenerator.assign_double("@" + ID,"%" + (LLVMGenerator.reg - 1));
                }
            }
            else
            {
                if( v.type == VarType.INT ){
                    LLVMGenerator.assign_i32("@" + ID,v.name);
                }
                if( v.type == VarType.REAL ){
                    LLVMGenerator.assign_double("@" + ID,v.name);
                }
            }
        }
        else{
            if(global_var.containsKey(v.name)){
                if( global_var.get(v.name) == VarType.INT ){
                    LLVMGenerator.load_i32("@" + v.name);
                    LLVMGenerator.assign_i32("%" + ID,"%" + (LLVMGenerator.reg - 1));
                }
                if( global_var.get(v.name) == VarType.REAL ){
                    LLVMGenerator.load_double("@" + v.name);
                    LLVMGenerator.assign_double("%" + ID,"%" + (LLVMGenerator.reg - 1));
                }
            }
            else if(local_var.containsKey(v.name)){
                if( local_var.get(v.name) == VarType.INT ){
                    LLVMGenerator.load_i32("%" + v.name);
                    LLVMGenerator.assign_i32("%" + ID, "%" + (LLVMGenerator.reg - 1));
                }
                if( local_var.get(v.name) == VarType.REAL ){
                    LLVMGenerator.load_double("%" + v.name);
                    LLVMGenerator.assign_double("%" + ID, "%" + (LLVMGenerator.reg - 1));
                }}
            else{
                if( v.type == VarType.INT ){
                    LLVMGenerator.assign_i32("%" + ID, v.name);
                }
                if( v.type == VarType.REAL ){
                    LLVMGenerator.assign_double("%" + ID, v.name);
                }
            }
        }}
    }}

    @Override //start loop
    public void exitRepetitions(DemoParser.RepetitionsContext ctx) {
        Value v1 = stack.pop();
        if( global_var.containsKey(v1.name)){
            LLVMGenerator.load_i32("@" +v1.name);
            LLVMGenerator.repeatstart("%" + (LLVMGenerator.reg-1), global);}
        else if( local_var.containsKey(v1.name)){
            LLVMGenerator.load_i32("%" +v1.name);
            LLVMGenerator.repeatstart("%" + (LLVMGenerator.reg-1), global);}
        else if(v1.type == VarType.INT)
            LLVMGenerator.repeatstart(v1.name,global);
        else {
            error(ctx.getStart().getLine(), "unknown variable "+v1.name);
        }

    }

    @Override//end of loop
    public void exitBlock(DemoParser.BlockContext ctx) {
        if( ctx.getParent() instanceof DemoParser.RepeatContext ){
            LLVMGenerator.repeatend();
        }
    }
    @Override
    public void enterProg(DemoParser.ProgContext ctx) {
        global = true;
    }

    @Override
    public void exitProg(DemoParser.ProgContext ctx) {
        LLVMGenerator.close_main();
        System.out.println( LLVMGenerator.generate() );
    }
    @Override
    public void exitFparam(DemoParser.FparamContext ctx) {
        String ID = ctx.ID().getText();
        functions.add(ID);
        function = ID;
        if(function_type==0){
            error(ctx.getStart().getLine(), "unknown function type ");}
        LLVMGenerator.functionstart(ID, function_type);
    }

    @Override//enter function
    public void enterFblock(DemoParser.FblockContext ctx) {
        global = false;
    }


    @Override//exit function
    public void exitFblock(DemoParser.FblockContext ctx) {
        if( ! local_var.containsKey(function) ){
            local_var.put(function, VarType.INT);
            LLVMGenerator.declare_i32(function,global);
            LLVMGenerator.assign_i32("%" + function, "0");
            function_type=1;
        }

        if (local_var.get(function) == VarType.INT) {
            LLVMGenerator.load_i32("%" + function);
            LLVMGenerator.functionend(function_type);
            local_var = new HashMap<String, VarType>();
            global = true;

        }
        else if(local_var.get(function) == VarType.REAL) {
            LLVMGenerator.load_double("%" + function);
            LLVMGenerator.functionend(function_type);
            local_var = new HashMap<String, VarType>();
            global = true;

        }
    }


    @Override
    public void exitIf(DemoParser.IfContext ctx) {
    }

    @Override
    public void enterBlockif(DemoParser.BlockifContext ctx) {
        LLVMGenerator.ifstart();
    }

    @Override
    public void exitBlockif(DemoParser.BlockifContext ctx) {
        LLVMGenerator.ifend();
    }

    @Override //check if the condition is equal to the value
    public void exitEqual(DemoParser.EqualContext ctx) {
        String ID = ctx.ID().getText();
        String INT = ctx.INT().getText();
        if( global_var.containsKey(ID) ) {
            LLVMGenerator.icmp("@" + ID, INT );
        }
        else if( local_var.containsKey(ID) ) {
            LLVMGenerator.icmp( "%" + ID, INT );
        }
        else {
            ctx.getStart().getLine();
            error(ctx.getStart().getLine(), "unknown variable "+ID);
        }
    }

    @Override //check if the condition is not equal to the value
    public void exitNegative(DemoParser.NegativeContext ctx) {
        String ID = ctx.ID().getText();
        String INT = ctx.INT().getText();
        if( global_var.containsKey(ID) ) {
            LLVMGenerator.icmpn( "@" +ID, INT );
        }
        else if( local_var.containsKey(ID) ) {
            LLVMGenerator.icmpn( "%" + ID, INT );
        }else {
            ctx.getStart().getLine();
            System.err.println("Line "+ ctx.getStart().getLine()+", unknown variable: "+ID);
        }
    }

    @Override //check if the condition is greater then the value
    public void exitGreater(DemoParser.GreaterContext ctx) {
        String ID = ctx.ID().getText();
        String INT = ctx.INT().getText();
        if( global_var.containsKey(ID) ) {
            LLVMGenerator.icmpg("@" + ID, INT );
        }
        else if( local_var.containsKey(ID) ) {
            LLVMGenerator.icmpg( "%" + ID, INT );
        }else {
            ctx.getStart().getLine();
            System.err.println("Line "+ ctx.getStart().getLine()+", unknown variable: "+ID);
        }
    }

    @Override //check if the condition is lower than the value
    public void exitLower(DemoParser.LowerContext ctx) {
        String ID = ctx.ID().getText();
        String INT = ctx.INT().getText();
        if( global_var.containsKey(ID) ) {
            LLVMGenerator.icmpl( "@" +ID, INT );
        }
        else if( local_var.containsKey(ID) ) {
            LLVMGenerator.icmpl( "%" + ID, INT );
        }
        else {
            ctx.getStart().getLine();
            System.err.println("Line "+ ctx.getStart().getLine()+", unknown variable: "+ID);
        }
    }

    @Override
    public void exitInt(DemoParser.IntContext ctx) {
        stack.push( new Value(ctx.INT().getText(), VarType.INT) );
    }

    @Override
    public void exitReal(DemoParser.RealContext ctx) {
        stack.push( new Value(ctx.REAL().getText(), VarType.REAL) );
    }

    @Override
    public void exitId(DemoParser.IdContext ctx) {
        String ID = ctx.ID().getText();
        if( functions.contains(ID) ) {
            LLVMGenerator.call(ID,function_type);
        }
        stack.push( new Value(ID, VarType.UNKNOWN) );
    }

    @Override//type of funstion is real
    public void exitFreal(DemoParser.FrealContext ctx) {
        function_type = 2;

    }
    @Override//type of function is int
    public void exitFint(DemoParser.FintContext ctx) {
        function_type = 1;

    }

    @Override
    public void exitAdd(DemoParser.AddContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if(global){
        if (global_var.containsKey(v1.name) && global_var.containsKey(v2.name)) {
            if (global_var.get(v1.name) == VarType.INT && (global_var.get(v2.name))== VarType.INT){
                LLVMGenerator.load_i32("@"+v1.name);
                LLVMGenerator.load_i32("@"+v2.name);
                LLVMGenerator.add_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
            else if (global_var.get(v1.name) == VarType.REAL && (global_var.get(v1.name))== VarType.REAL){
                LLVMGenerator.load_double("@"+v1.name);
                LLVMGenerator.load_double("@"+v2.name);
                LLVMGenerator.add_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
            else {
                error(ctx.getStart().getLine(), "Add type mismatch");
            }
        } else if ((global_var.containsKey(v1.name))) {
            if (global_var.get(v1.name) == VarType.INT && v2.type== VarType.INT){
                LLVMGenerator.load_i32("@"+v1.name);
                LLVMGenerator.add_i32("%" + (LLVMGenerator.reg - 1), v2.name);
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
            else if (global_var.get(v1.name) == VarType.REAL && v2.type== VarType.REAL){
                LLVMGenerator.load_double("@"+v1.name);
                LLVMGenerator.add_double("%" + (LLVMGenerator.reg - 1), v2.name);
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
            else {
                System.out.println("error1");
                error(ctx.getStart().getLine(), "Add type mismatch");
            }
        } else if ((global_var.containsKey(v2.name))) {
            if (global_var.get(v2.name) == VarType.INT && v1.type== VarType.INT){
                LLVMGenerator.load_i32("@"+v2.name);
                LLVMGenerator.add_i32(v1.name, "%" + (LLVMGenerator.reg - 1));
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
            else if (global_var.get(v2.name) == VarType.REAL && v1.type== VarType.REAL){
                LLVMGenerator.load_double("@"+v2.name);
                LLVMGenerator.add_double(v1.name, "%" + (LLVMGenerator.reg - 1));
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
            else {
                error(ctx.getStart().getLine(), "Add type mismatch");
            }
        }

        else if(!global_var.containsKey(v1.name) && !global_var.containsKey(v2.name)&& v1.type == v2.type) {
            if (v1.type == VarType.INT) {
                LLVMGenerator.add_i32(v1.name, v2.name);
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));
            }
            if (v1.type == VarType.REAL) {
                LLVMGenerator.add_double(v1.name, v2.name);
                stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));
            }
        }
        else {
            error(ctx.getStart().getLine(), "Add type mismatch");
        }}
        else{
            if (global_var.containsKey(v1.name) && global_var.containsKey(v2.name)) {
                if (global_var.get(v1.name) == VarType.INT && (global_var.get(v2.name))== VarType.INT){
                    LLVMGenerator.load_i32("@"+v1.name);
                    LLVMGenerator.load_i32("@"+v2.name);
                    LLVMGenerator.add_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v1.name) == VarType.REAL && (global_var.get(v1.name))== VarType.REAL){
                    LLVMGenerator.load_double("@"+v1.name);
                    LLVMGenerator.load_double("@"+v2.name);
                    LLVMGenerator.add_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "Add type mismatch");
                }
            }
            else if ((global_var.containsKey(v1.name))&&local_var.containsKey(v2.name)) {
                if (global_var.get(v1.name) == VarType.INT && local_var.get(v2.name)== VarType.INT){
                    LLVMGenerator.load_i32("@"+v1.name);
                    LLVMGenerator.load_i32("%"+v2.name);
                    LLVMGenerator.add_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v1.name) == VarType.REAL && local_var.get(v2.name)== VarType.REAL){
                    LLVMGenerator.load_double("@"+v1.name);
                    LLVMGenerator.load_double("%"+v2.name);
                    LLVMGenerator.add_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    System.out.println("error1");
                    error(ctx.getStart().getLine(), "Add type mismatch");
                }
            } else if ((global_var.containsKey(v2.name))&&local_var.containsKey(v1.name)) {
                if (global_var.get(v2.name) == VarType.INT && local_var.get(v1.name)== VarType.INT){
                    LLVMGenerator.load_i32("%"+v1.name);
                    LLVMGenerator.load_i32("@"+v2.name);
                    LLVMGenerator.add_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v2.name) == VarType.REAL && local_var.get(v1.name)== VarType.REAL){
                    LLVMGenerator.load_double("%"+v1.name);
                    LLVMGenerator.load_double("@"+v2.name);
                    LLVMGenerator.add_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "Add type mismatch");
                }
            }
            else if ((global_var.containsKey(v1.name))) {
                if (global_var.get(v1.name) == VarType.INT && v2.type== VarType.INT){
                    LLVMGenerator.load_i32("@"+v1.name);
                    LLVMGenerator.add_i32("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v1.name) == VarType.REAL && v2.type== VarType.REAL){
                    LLVMGenerator.load_double("@"+v1.name);
                    LLVMGenerator.add_double("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    System.out.println("error1");
                    error(ctx.getStart().getLine(), "Add type mismatch");
                }
            } else if ((global_var.containsKey(v2.name))) {
                if (global_var.get(v2.name) == VarType.INT && v1.type== VarType.INT){
                    LLVMGenerator.load_i32("@"+v2.name);
                    LLVMGenerator.add_i32(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v2.name) == VarType.REAL && v1.type== VarType.REAL){
                    LLVMGenerator.load_double("@"+v2.name);
                    LLVMGenerator.add_double(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "Add type mismatch");
                }
            }
            else if (local_var.containsKey(v1.name) && local_var.containsKey(v2.name)) {
                if (local_var.get(v1.name) == VarType.INT && (local_var.get(v2.name))== VarType.INT){
                    LLVMGenerator.load_i32("%"+v1.name);
                    LLVMGenerator.load_i32("%"+v2.name);
                    LLVMGenerator.add_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (local_var.get(v1.name) == VarType.REAL && (local_var.get(v1.name))== VarType.REAL){
                    LLVMGenerator.load_double("%"+v1.name);
                    LLVMGenerator.load_double("%"+v2.name);
                    LLVMGenerator.add_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "Add type mismatch");
                }
            } else if ((local_var.containsKey(v1.name))) {
                if (local_var.get(v1.name) == VarType.INT && v2.type== VarType.INT){
                    LLVMGenerator.load_i32("%"+v1.name);
                    LLVMGenerator.add_i32("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (local_var.get(v1.name) == VarType.REAL && v2.type== VarType.REAL){
                    LLVMGenerator.load_double("%"+v1.name);
                    LLVMGenerator.add_double("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    System.out.println("error1");
                    error(ctx.getStart().getLine(), "Add type mismatch");
                }
            } else if ((local_var.containsKey(v2.name))) {
                if (local_var.get(v2.name) == VarType.INT && v1.type== VarType.INT){
                    LLVMGenerator.load_i32("%" + v2.name);
                    LLVMGenerator.add_i32(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (local_var.get(v2.name) == VarType.REAL && v1.type== VarType.REAL){
                    LLVMGenerator.load_double("%" + v2.name);
                    LLVMGenerator.add_double(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "Add type mismatch");
                }
            }

            else if(!local_var.containsKey(v1.name) && !local_var.containsKey(v2.name)&& v1.type == v2.type) {
                if (v1.type == VarType.INT) {
                    LLVMGenerator.add_i32(v1.name, v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));
                }
                if (v1.type == VarType.REAL) {
                    LLVMGenerator.add_double(v1.name, v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));
                }
            }
            else {
                error(ctx.getStart().getLine(), "Add type mismatch");
            }
        }
    }

    @Override
    public void exitSub(DemoParser.SubContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if(global){
            if (global_var.containsKey(v1.name) && global_var.containsKey(v2.name)) {
                if (global_var.get(v1.name) == VarType.INT && (global_var.get(v2.name))== VarType.INT){
                    LLVMGenerator.load_i32("@"+v1.name);
                    LLVMGenerator.load_i32("@"+v2.name);
                    LLVMGenerator.sub_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v1.name) == VarType.REAL && (global_var.get(v1.name))== VarType.REAL){
                    LLVMGenerator.load_double("@"+v1.name);
                    LLVMGenerator.load_double("@"+v2.name);
                    LLVMGenerator.sub_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "Sub type mismatch");
                }
            } else if ((global_var.containsKey(v1.name))) {
                if (global_var.get(v1.name) == VarType.INT && v2.type== VarType.INT){
                    LLVMGenerator.load_i32("@"+v1.name);
                    LLVMGenerator.sub_i32("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v1.name) == VarType.REAL && v2.type== VarType.REAL){
                    LLVMGenerator.load_double("@"+v1.name);
                    LLVMGenerator.sub_double("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    System.out.println("error1");
                    error(ctx.getStart().getLine(), "sub type mismatch");
                }
            } else if ((global_var.containsKey(v2.name))) {
                if (global_var.get(v2.name) == VarType.INT && v1.type== VarType.INT){
                    LLVMGenerator.load_i32("@"+v2.name);
                    LLVMGenerator.sub_i32(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v2.name) == VarType.REAL && v1.type== VarType.REAL){
                    LLVMGenerator.load_double("@"+v2.name);
                    LLVMGenerator.sub_double(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "sub type mismatch");
                }
            }

            else if(!global_var.containsKey(v1.name) && !global_var.containsKey(v2.name)&& v1.type == v2.type) {
                if (v1.type == VarType.INT) {
                    LLVMGenerator.sub_i32(v1.name, v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));
                }
                if (v1.type == VarType.REAL) {
                    LLVMGenerator.sub_double(v1.name, v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));
                }
            }
            else {
                error(ctx.getStart().getLine(), "sub type mismatch");
            }}
        else{
            if (global_var.containsKey(v1.name) && global_var.containsKey(v2.name)) {
                if (global_var.get(v1.name) == VarType.INT && (global_var.get(v2.name))== VarType.INT){
                    LLVMGenerator.load_i32("@"+v1.name);
                    LLVMGenerator.load_i32("@"+v2.name);
                    LLVMGenerator.sub_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v1.name) == VarType.REAL && (global_var.get(v1.name))== VarType.REAL){
                    LLVMGenerator.load_double("@"+v1.name);
                    LLVMGenerator.load_double("@"+v2.name);
                    LLVMGenerator.sub_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "sub type mismatch");
                }
            }
            else if ((global_var.containsKey(v1.name))&&local_var.containsKey(v2.name)) {
                if (global_var.get(v1.name) == VarType.INT && local_var.get(v2.name)== VarType.INT){
                    LLVMGenerator.load_i32("@"+v1.name);
                    LLVMGenerator.load_i32("%"+v2.name);
                    LLVMGenerator.sub_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v1.name) == VarType.REAL && local_var.get(v2.name)== VarType.REAL){
                    LLVMGenerator.load_double("@"+v1.name);
                    LLVMGenerator.load_double("%"+v2.name);
                    LLVMGenerator.sub_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    System.out.println("error1");
                    error(ctx.getStart().getLine(), "Add type mismatch");
                }
            } else if ((global_var.containsKey(v2.name))&&local_var.containsKey(v1.name)) {
                if (global_var.get(v2.name) == VarType.INT && local_var.get(v1.name)== VarType.INT){
                    LLVMGenerator.load_i32("%"+v1.name);
                    LLVMGenerator.load_i32("@"+v2.name);
                    LLVMGenerator.sub_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v2.name) == VarType.REAL && local_var.get(v1.name)== VarType.REAL){
                    LLVMGenerator.load_double("%"+v1.name);
                    LLVMGenerator.load_double("@"+v2.name);
                    LLVMGenerator.sub_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "sub type mismatch");
                }
            }
            else if ((global_var.containsKey(v1.name))) {
                if (global_var.get(v1.name) == VarType.INT && v2.type== VarType.INT){
                    LLVMGenerator.load_i32("@"+v1.name);
                    LLVMGenerator.sub_i32("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v1.name) == VarType.REAL && v2.type== VarType.REAL){
                    LLVMGenerator.load_double("@"+v1.name);
                    LLVMGenerator.sub_double("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    System.out.println("error1");
                    error(ctx.getStart().getLine(), "sub type mismatch");
                }
            } else if ((global_var.containsKey(v2.name))) {
                if (global_var.get(v2.name) == VarType.INT && v1.type== VarType.INT){
                    LLVMGenerator.load_i32("@"+v2.name);
                    LLVMGenerator.sub_i32(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v2.name) == VarType.REAL && v1.type== VarType.REAL){
                    LLVMGenerator.load_double("@"+v2.name);
                    LLVMGenerator.sub_double(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "sub type mismatch");
                }
            }
            else if (local_var.containsKey(v1.name) && local_var.containsKey(v2.name)) {
                if (local_var.get(v1.name) == VarType.INT && (local_var.get(v2.name))== VarType.INT){
                    LLVMGenerator.load_i32("%"+v1.name);
                    LLVMGenerator.load_i32("%"+v2.name);
                    LLVMGenerator.sub_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (local_var.get(v1.name) == VarType.REAL && (local_var.get(v1.name))== VarType.REAL){
                    LLVMGenerator.load_double("%"+v1.name);
                    LLVMGenerator.load_double("%"+v2.name);
                    LLVMGenerator.sub_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "sub type mismatch");
                }
            } else if ((local_var.containsKey(v1.name))) {
                if (local_var.get(v1.name) == VarType.INT && v2.type== VarType.INT){
                    LLVMGenerator.load_i32("%"+v1.name);
                    LLVMGenerator.sub_i32("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (local_var.get(v1.name) == VarType.REAL && v2.type== VarType.REAL){
                    LLVMGenerator.load_double("%"+v1.name);
                    LLVMGenerator.sub_double("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    System.out.println("error1");
                    error(ctx.getStart().getLine(), "sub type mismatch");
                }
            } else if ((local_var.containsKey(v2.name))) {
                if (local_var.get(v2.name) == VarType.INT && v1.type== VarType.INT){
                    LLVMGenerator.load_i32("%" + v2.name);
                    LLVMGenerator.sub_i32(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (local_var.get(v2.name) == VarType.REAL && v1.type== VarType.REAL){
                    LLVMGenerator.load_double("%" + v2.name);
                    LLVMGenerator.sub_double(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "sub type mismatch");
                }
            }

            else if(!local_var.containsKey(v1.name) && !local_var.containsKey(v2.name)&& v1.type == v2.type) {
                if (v1.type == VarType.INT) {
                    LLVMGenerator.sub_i32(v1.name, v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));
                }
                if (v1.type == VarType.REAL) {
                    LLVMGenerator.sub_double(v1.name, v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));
                }
            }
            else {
                error(ctx.getStart().getLine(), "Add type mismatch");
            }
        }
    }

    @Override
    public void exitMult(DemoParser.MultContext ctx) {
        Value v1 = stack.pop();
        Value v2 = stack.pop();
        if(global){
            if (global_var.containsKey(v1.name) && global_var.containsKey(v2.name)) {
                if (global_var.get(v1.name) == VarType.INT && (global_var.get(v2.name))== VarType.INT){
                    LLVMGenerator.load_i32("@"+v1.name);
                    LLVMGenerator.load_i32("@"+v2.name);
                    LLVMGenerator.mult_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v1.name) == VarType.REAL && (global_var.get(v1.name))== VarType.REAL){
                    LLVMGenerator.load_double("@"+v1.name);
                    LLVMGenerator.load_double("@"+v2.name);
                    LLVMGenerator.mult_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "mult type mismatch");
                }
            } else if ((global_var.containsKey(v1.name))) {
                if (global_var.get(v1.name) == VarType.INT && v2.type== VarType.INT){
                    LLVMGenerator.load_i32("@"+v1.name);
                    LLVMGenerator.mult_i32("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v1.name) == VarType.REAL && v2.type== VarType.REAL){
                    LLVMGenerator.load_double("@"+v1.name);
                    LLVMGenerator.mult_double("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    System.out.println("error1");
                    error(ctx.getStart().getLine(), "mult type mismatch");
                }
            } else if ((global_var.containsKey(v2.name))) {
                if (global_var.get(v2.name) == VarType.INT && v1.type== VarType.INT){
                    LLVMGenerator.load_i32("@"+v2.name);
                    LLVMGenerator.mult_i32(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v2.name) == VarType.REAL && v1.type== VarType.REAL){
                    LLVMGenerator.load_double("@"+v2.name);
                    LLVMGenerator.mult_double(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "mult type mismatch");
                }
            }

            else if(!global_var.containsKey(v1.name) && !global_var.containsKey(v2.name)&& v1.type == v2.type) {
                if (v1.type == VarType.INT) {
                    LLVMGenerator.mult_i32(v1.name, v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));
                }
                if (v1.type == VarType.REAL) {
                    LLVMGenerator.mult_double(v1.name, v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));
                }
            }
            else {
                error(ctx.getStart().getLine(), "mult type mismatch");
            }}
        else{
            if (global_var.containsKey(v1.name) && global_var.containsKey(v2.name)) {
                if (global_var.get(v1.name) == VarType.INT && (global_var.get(v2.name))== VarType.INT){
                    LLVMGenerator.load_i32("@"+v1.name);
                    LLVMGenerator.load_i32("@"+v2.name);
                    LLVMGenerator.mult_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v1.name) == VarType.REAL && (global_var.get(v1.name))== VarType.REAL){
                    LLVMGenerator.load_double("@"+v1.name);
                    LLVMGenerator.load_double("@"+v2.name);
                    LLVMGenerator.mult_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "mult type mismatch");
                }
            }
            else if ((global_var.containsKey(v1.name))&&local_var.containsKey(v2.name)) {
                if (global_var.get(v1.name) == VarType.INT && local_var.get(v2.name)== VarType.INT){
                    LLVMGenerator.load_i32("@"+v1.name);
                    LLVMGenerator.load_i32("%"+v2.name);
                    LLVMGenerator.mult_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v1.name) == VarType.REAL && local_var.get(v2.name)== VarType.REAL){
                    LLVMGenerator.load_double("@"+v1.name);
                    LLVMGenerator.load_double("%"+v2.name);
                    LLVMGenerator.mult_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    System.out.println("error1");
                    error(ctx.getStart().getLine(), "mult type mismatch");
                }
            } else if ((global_var.containsKey(v2.name))&&local_var.containsKey(v1.name)) {
                if (global_var.get(v2.name) == VarType.INT && local_var.get(v1.name)== VarType.INT){
                    LLVMGenerator.load_i32("%"+v1.name);
                    LLVMGenerator.load_i32("@"+v2.name);
                    LLVMGenerator.mult_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v2.name) == VarType.REAL && local_var.get(v1.name)== VarType.REAL){
                    LLVMGenerator.load_double("%"+v1.name);
                    LLVMGenerator.load_double("@"+v2.name);
                    LLVMGenerator.mult_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "mult type mismatch");
                }
            }
            else if ((global_var.containsKey(v1.name))) {
                if (global_var.get(v1.name) == VarType.INT && v2.type== VarType.INT){
                    LLVMGenerator.load_i32("@"+v1.name);
                    LLVMGenerator.mult_i32("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v1.name) == VarType.REAL && v2.type== VarType.REAL){
                    LLVMGenerator.load_double("@"+v1.name);
                    LLVMGenerator.mult_double("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    System.out.println("error1");
                    error(ctx.getStart().getLine(), "mult type mismatch");
                }
            } else if ((global_var.containsKey(v2.name))) {
                if (global_var.get(v2.name) == VarType.INT && v1.type== VarType.INT){
                    LLVMGenerator.load_i32("@"+v2.name);
                    LLVMGenerator.mult_i32(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (global_var.get(v2.name) == VarType.REAL && v1.type== VarType.REAL){
                    LLVMGenerator.load_double("@"+v2.name);
                    LLVMGenerator.mult_double(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "mult type mismatch");
                }
            }
            else if (local_var.containsKey(v1.name) && local_var.containsKey(v2.name)) {
                if (local_var.get(v1.name) == VarType.INT && (local_var.get(v2.name))== VarType.INT){
                    LLVMGenerator.load_i32("%"+v1.name);
                    LLVMGenerator.load_i32("%"+v2.name);
                    LLVMGenerator.mult_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (local_var.get(v1.name) == VarType.REAL && (local_var.get(v1.name))== VarType.REAL){
                    LLVMGenerator.load_double("%"+v1.name);
                    LLVMGenerator.load_double("%"+v2.name);
                    LLVMGenerator.mult_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "mult type mismatch");
                }
            } else if ((local_var.containsKey(v1.name))) {
                if (local_var.get(v1.name) == VarType.INT && v2.type== VarType.INT){
                    LLVMGenerator.load_i32("%"+v1.name);
                    LLVMGenerator.mult_i32("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (local_var.get(v1.name) == VarType.REAL && v2.type== VarType.REAL){
                    LLVMGenerator.load_double("%"+v1.name);
                    LLVMGenerator.mult_double("%" + (LLVMGenerator.reg - 1), v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    System.out.println("error1");
                    error(ctx.getStart().getLine(), "mult type mismatch");
                }
            } else if ((local_var.containsKey(v2.name))) {
                if (local_var.get(v2.name) == VarType.INT && v1.type== VarType.INT){
                    LLVMGenerator.load_i32("%" + v2.name);
                    LLVMGenerator.mult_i32(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                else if (local_var.get(v2.name) == VarType.REAL && v1.type== VarType.REAL){
                    LLVMGenerator.load_double("%" + v2.name);
                    LLVMGenerator.mult_double(v1.name, "%" + (LLVMGenerator.reg - 1));
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                else {
                    error(ctx.getStart().getLine(), "mult type mismatch");
                }
            }

            else if(!local_var.containsKey(v1.name) && !local_var.containsKey(v2.name)&& v1.type == v2.type) {
                if (v1.type == VarType.INT) {
                    LLVMGenerator.mult_i32(v1.name, v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));
                }
                if (v1.type == VarType.REAL) {
                    LLVMGenerator.mult_double(v1.name, v2.name);
                    stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));
                }
            }
            else {
                error(ctx.getStart().getLine(), "Add type mismatch");
            }
        }
    }

    @Override
    public void exitDiv(DemoParser.DivContext ctx) {
            Value v1 = stack.pop();
            Value v2 = stack.pop();
            if(global){
                if (global_var.containsKey(v1.name) && global_var.containsKey(v2.name)) {
                    if (global_var.get(v1.name) == VarType.INT && (global_var.get(v2.name))== VarType.INT){
                        LLVMGenerator.load_i32("@"+v1.name);
                        LLVMGenerator.load_i32("@"+v2.name);
                        LLVMGenerator.div_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                    else if (global_var.get(v1.name) == VarType.REAL && (global_var.get(v1.name))== VarType.REAL){
                        LLVMGenerator.load_double("@"+v1.name);
                        LLVMGenerator.load_double("@"+v2.name);
                        LLVMGenerator.div_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                    else {
                        error(ctx.getStart().getLine(), "div type mismatch");
                    }
                } else if ((global_var.containsKey(v1.name))) {
                    if (global_var.get(v1.name) == VarType.INT && v2.type== VarType.INT){
                        LLVMGenerator.load_i32("@"+v1.name);
                        LLVMGenerator.div_i32("%" + (LLVMGenerator.reg - 1), v2.name);
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                    else if (global_var.get(v1.name) == VarType.REAL && v2.type== VarType.REAL){
                        LLVMGenerator.load_double("@"+v1.name);
                        LLVMGenerator.div_double("%" + (LLVMGenerator.reg - 1), v2.name);
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                    else {
                        System.out.println("error1");
                        error(ctx.getStart().getLine(), "div type mismatch");
                    }
                } else if ((global_var.containsKey(v2.name))) {
                    if (global_var.get(v2.name) == VarType.INT && v1.type== VarType.INT){
                        LLVMGenerator.load_i32("@"+v2.name);
                        LLVMGenerator.div_i32(v1.name, "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                    else if (global_var.get(v2.name) == VarType.REAL && v1.type== VarType.REAL){
                        LLVMGenerator.load_double("@"+v2.name);
                        LLVMGenerator.div_double(v1.name, "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                    else {
                        error(ctx.getStart().getLine(), "div type mismatch");
                    }
                }

                else if(!global_var.containsKey(v1.name) && !global_var.containsKey(v2.name)&& v1.type == v2.type) {
                    if (v1.type == VarType.INT) {
                        LLVMGenerator.div_i32(v1.name, v2.name);
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));
                    }
                    if (v1.type == VarType.REAL) {
                        LLVMGenerator.div_double(v1.name, v2.name);
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));
                    }
                }
                else {
                    error(ctx.getStart().getLine(), "div type mismatch");
                }}
            else{
                if (global_var.containsKey(v1.name) && global_var.containsKey(v2.name)) {
                    if (global_var.get(v1.name) == VarType.INT && (global_var.get(v2.name))== VarType.INT){
                        LLVMGenerator.load_i32("@"+v1.name);
                        LLVMGenerator.load_i32("@"+v2.name);
                        LLVMGenerator.div_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                    else if (global_var.get(v1.name) == VarType.REAL && (global_var.get(v1.name))== VarType.REAL){
                        LLVMGenerator.load_double("@"+v1.name);
                        LLVMGenerator.load_double("@"+v2.name);
                        LLVMGenerator.div_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                    else {
                        error(ctx.getStart().getLine(), "div type mismatch");
                    }
                }
                else if ((global_var.containsKey(v1.name))&&local_var.containsKey(v2.name)) {
                    if (global_var.get(v1.name) == VarType.INT && local_var.get(v2.name)== VarType.INT){
                        LLVMGenerator.load_i32("@"+v1.name);
                        LLVMGenerator.load_i32("%"+v2.name);
                        LLVMGenerator.div_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                    else if (global_var.get(v1.name) == VarType.REAL && local_var.get(v2.name)== VarType.REAL){
                        LLVMGenerator.load_double("@"+v1.name);
                        LLVMGenerator.load_double("%"+v2.name);
                        LLVMGenerator.div_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                    else {
                        System.out.println("error1");
                        error(ctx.getStart().getLine(), "div type mismatch");
                    }
                } else if ((global_var.containsKey(v2.name))&&local_var.containsKey(v1.name)) {
                    if (global_var.get(v2.name) == VarType.INT && local_var.get(v1.name)== VarType.INT){
                        LLVMGenerator.load_i32("%"+v1.name);
                        LLVMGenerator.load_i32("@"+v2.name);
                        LLVMGenerator.div_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                    else if (global_var.get(v2.name) == VarType.REAL && local_var.get(v1.name)== VarType.REAL){
                        LLVMGenerator.load_double("%"+v1.name);
                        LLVMGenerator.load_double("@"+v2.name);
                        LLVMGenerator.div_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                    else {
                        error(ctx.getStart().getLine(), "div type mismatch");
                    }
                }
                else if ((global_var.containsKey(v1.name))) {
                    if (global_var.get(v1.name) == VarType.INT && v2.type== VarType.INT){
                        LLVMGenerator.load_i32("@"+v1.name);
                        LLVMGenerator.div_i32("%" + (LLVMGenerator.reg - 1), v2.name);
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                    else if (global_var.get(v1.name) == VarType.REAL && v2.type== VarType.REAL){
                        LLVMGenerator.load_double("@"+v1.name);
                        LLVMGenerator.div_double("%" + (LLVMGenerator.reg - 1), v2.name);
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                    else {
                        System.out.println("error1");
                        error(ctx.getStart().getLine(), "div type mismatch");
                    }
                } else if ((global_var.containsKey(v2.name))) {
                    if (global_var.get(v2.name) == VarType.INT && v1.type== VarType.INT){
                        LLVMGenerator.load_i32("@"+v2.name);
                        LLVMGenerator.div_i32(v1.name, "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                    else if (global_var.get(v2.name) == VarType.REAL && v1.type== VarType.REAL){
                        LLVMGenerator.load_double("@"+v2.name);
                        LLVMGenerator.div_double(v1.name, "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                    else {
                        error(ctx.getStart().getLine(), "div type mismatch");
                    }
                }
                else if (local_var.containsKey(v1.name) && local_var.containsKey(v2.name)) {
                    if (local_var.get(v1.name) == VarType.INT && (local_var.get(v2.name))== VarType.INT){
                        LLVMGenerator.load_i32("%"+v1.name);
                        LLVMGenerator.load_i32("%"+v2.name);
                        LLVMGenerator.div_i32("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                    else if (local_var.get(v1.name) == VarType.REAL && (local_var.get(v1.name))== VarType.REAL){
                        LLVMGenerator.load_double("%"+v1.name);
                        LLVMGenerator.load_double("%"+v2.name);
                        LLVMGenerator.div_double("%" + (LLVMGenerator.reg - 2), "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                    else {
                        error(ctx.getStart().getLine(), "div type mismatch");
                    }
                } else if ((local_var.containsKey(v1.name))) {
                    if (local_var.get(v1.name) == VarType.INT && v2.type== VarType.INT){
                        LLVMGenerator.load_i32("%"+v1.name);
                        LLVMGenerator.div_i32("%" + (LLVMGenerator.reg - 1), v2.name);
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                    else if (local_var.get(v1.name) == VarType.REAL && v2.type== VarType.REAL){
                        LLVMGenerator.load_double("%"+v1.name);
                        LLVMGenerator.div_double("%" + (LLVMGenerator.reg - 1), v2.name);
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                    else {
                        System.out.println("error1");
                        error(ctx.getStart().getLine(), "div type mismatch");
                    }
                } else if ((local_var.containsKey(v2.name))) {
                    if (local_var.get(v2.name) == VarType.INT && v1.type== VarType.INT){
                        LLVMGenerator.load_i32("%" + v2.name);
                        LLVMGenerator.div_i32(v1.name, "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));}
                    else if (local_var.get(v2.name) == VarType.REAL && v1.type== VarType.REAL){
                        LLVMGenerator.load_double("%" + v2.name);
                        LLVMGenerator.div_double(v1.name, "%" + (LLVMGenerator.reg - 1));
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));}
                    else {
                        error(ctx.getStart().getLine(), "div type mismatch");
                    }
                }

                else if(!local_var.containsKey(v1.name) && !local_var.containsKey(v2.name)&& v1.type == v2.type) {
                    if (v1.type == VarType.INT) {
                        LLVMGenerator.div_i32(v1.name, v2.name);
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.INT));
                    }
                    if (v1.type == VarType.REAL) {
                        LLVMGenerator.div_double(v1.name, v2.name);
                        stack.push(new Value("%" + (LLVMGenerator.reg - 1), VarType.REAL));
                    }
                }
                else {
                    error(ctx.getStart().getLine(), "Add type mismatch");
                }
            }
        }


    @Override
    public void exitToint(DemoParser.TointContext ctx) {
        Value v = stack.pop();
        LLVMGenerator.fptosi( v.name );
        stack.push( new Value("%"+(LLVMGenerator.reg-1), VarType.INT) );
    }

    @Override
    public void exitToreal(DemoParser.TorealContext ctx) {
        Value v = stack.pop();
        LLVMGenerator.sitofp( v.name );
        stack.push( new Value("%"+(LLVMGenerator.reg-1), VarType.REAL) );
    }

    @Override//write function - it prints value
    public void exitWrite(DemoParser.WriteContext ctx) {
        String ID = ctx.ID().getText();
        if(global_var.containsKey(ID)){
        VarType type = global_var.get(ID);
        if( type != null ) {
            if( type == VarType.INT ){
                LLVMGenerator.printf_i32("@"+ ID );
            }
            if( type == VarType.REAL ){
                LLVMGenerator.printf_double("@"+ ID );
            }
        } else {
            error(ctx.getStart().getLine(), "unknown variable "+ID);
        }}
        else if(local_var.containsKey(ID)){
            VarType type = local_var.get(ID);
            if( type != null ) {
                if( type == VarType.INT ){
                    LLVMGenerator.printf_i32("%"+ ID );
                }
                if( type == VarType.REAL ){
                    LLVMGenerator.printf_double("%"+ ID );
                }
            } else {
                error(ctx.getStart().getLine(), "unknown variable "+ID);
            }}
    }

    @Override//read from keyboard
    public void exitRead(DemoParser.ReadContext ctx) {
        String ID = ctx.ID().getText();
        if(global){
        if( ! global_var.containsKey(ID) ) {
            global_var.put(ID, VarType.INT );
            LLVMGenerator.declare_i32(ID,global);
        }
            LLVMGenerator.scanf("@"+ID);
        }
        else{
            if( ! local_var.containsKey(ID) ) {
                local_var.put(ID, VarType.INT );
                LLVMGenerator.declare_i32(ID,global);
        }
        LLVMGenerator.scanf("%"+ID);
    }}

    void error(int line, String msg){
        System.err.println("Error, line "+line+", "+msg);
        System.exit(1);
    }

}