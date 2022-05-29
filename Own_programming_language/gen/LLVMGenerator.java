import java.util.Stack;
class LLVMGenerator{

    static String header_text = "";
    static String main_text = "";
    static int reg = 1;
    static int br = 0;
    static int tmp = 1;
    static String buffer = "";

    static Stack<Integer> brstack = new Stack<Integer>();

    static void functionstart(String id, int function_type){
        main_text += buffer;
        tmp= reg;
        if(function_type == 1)
            buffer = "define i32 @"+id+"() nounwind {\n";
        else if(function_type==2){
            buffer = "define double @"+id+"() nounwind {\n";
        }
        reg = 1;
    }

    static void functionend(Integer function_type){
        if(function_type==1)
        buffer += "ret i32 %"+(reg-1)+"\n";
        else if(function_type==2){
            buffer += "ret double %"+(reg-1)+"\n";
        }
        buffer += "}\n";
        header_text += buffer;
        buffer = "";
        reg = tmp;
    }

    static void repeatstart(String repetitions, Boolean global){
        declare_i32(Integer.toString(reg), false);
        int counter = reg;
        reg++;
        assign_i32("%"+ Integer.toString(counter), "0");
        br++;
        buffer += "br label %cond"+br+"\n";
        buffer += "cond"+br+":\n";

        load_i32("%"+Integer.toString(counter));
        add_i32("%"+(reg-1), "1");
        assign_i32("%"+Integer.toString(counter), "%"+(reg-1));

        buffer += "%"+reg+" = icmp slt i32 %"+(reg-2)+", "+repetitions+"\n";
        reg++;

        buffer += "br i1 %"+(reg-1)+", label %true"+br+", label %false"+br+"\n";
        buffer += "true"+br+":\n";
        brstack.push(br);
    }

    static void repeatend(){
        int b = brstack.pop();
        buffer += "br label %cond"+b+"\n";
        buffer += "false"+b+":\n";
    }

    static void icmp(String id, String value){
        buffer += "%"+reg+" = load i32, i32* "+id+"\n";
        reg++;
        buffer += "%"+reg+" = icmp eq i32 %"+(reg-1)+", "+value+"\n";
        reg++;
    }

    static void icmpn(String id, String value){
        buffer += "%"+reg+" = load i32, i32* "+id+"\n";
        reg++;
        buffer += "%"+reg+" = icmp ne i32 %"+(reg-1)+", "+value+"\n";
        reg++;
    }

    static void icmpg(String id, String value){
        buffer += "%"+reg+" = load i32, i32* "+id+"\n";
        reg++;
        buffer += "%"+reg+" = icmp sgt i32 %"+(reg-1)+", "+value+"\n";
        reg++;
    }

    static void icmpl(String id, String value){
        buffer += "%"+reg+" = load i32, i32* "+id+"\n";
        reg++;
        buffer += "%"+reg+" = icmp ugt i32 %"+(reg-1)+", "+value+"\n";
        reg++;
    }

    static void ifstart(){
        br++;
        buffer += "br i1 %"+(reg-1)+", label %true"+br+", label %false"+br+"\n";
        buffer += "true"+br+":\n";
        brstack.push(br);
    }

    static void ifend(){
        int b = brstack.pop();
        buffer += "br label %false"+b+"\n";
        buffer += "false"+b+":\n";
    }

    static void printf_i32(String id){
        buffer += "%"+reg+" = load i32, i32* "+id+"\n";
        reg++;
        buffer += "%"+reg+" = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 %"+(reg-1)+")\n";
        reg++;
    }

    static void printf_double(String id){
        buffer += "%"+reg+" = load double, double* "+id+"\n";
        reg++;
        buffer += "%"+reg+" = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpd, i32 0, i32 0), double %"+(reg-1)+")\n";
        reg++;
    }

    static void scanf(String id){
        buffer += "%"+reg+" = call i32 (i8*, ...) @__isoc99_scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @strs, i32 0, i32 0), i32* "+id+")\n";
        reg++;
    }

    static void declare_i32(String id, Boolean global){
        if( global ){
            header_text += "@"+id+" = global i32 0\n";
        } else {
            buffer += "%"+id+" = alloca i32\n";
        }
    }

    static void declare_double(String id, Boolean global){
        if( global ){
            header_text += "@"+id+" = global double 0.0\n";
        } else {
            buffer += "%"+id+" = alloca double\n";
        }
    }

    static void assign_i32(String id, String value){
        buffer += "store i32 "+value+", i32* "+id+"\n";
    }

    static void assign_double(String id, String value){
        buffer += "store double "+value+", double* "+id+"\n";
    }


    static void load_i32(String id){
        buffer += "%"+reg+" = load i32, i32* "+id+"\n";
        reg++;
    }

    static void load_double(String id){
        buffer += "%"+reg+" = load double, double* "+id+"\n";
        reg++;
    }

    static void add_i32(String val1, String val2){
        buffer += "%"+reg+" = add i32 "+val1+", "+val2+"\n";
        reg++;
    }

    static void add_double(String val1, String val2){
        buffer += "%"+reg+" = fadd double "+val1+", "+val2+"\n";
        reg++;
    }

    static void sub_i32(String val1, String val2){
        buffer += "%"+reg+" = sub i32 "+val2+", "+val1+"\n";
        reg++;
    }

    static void sub_double(String val1, String val2){
        buffer += "%"+reg+" = fsub double "+val2+", "+val1+"\n";
        reg++;
    }



    static void mult_i32(String val1, String val2){
        buffer += "%"+reg+" = mul i32 "+val1+", "+val2+"\n";
        reg++;
    }

    static void mult_double(String val1, String val2){
        buffer += "%"+reg+" = fmul double "+val1+", "+val2+"\n";
        reg++;
    }

    static void div_i32(String val1, String val2){
        buffer += "%"+reg+" = udiv i32 "+val2+", "+val1+"\n";
        reg++;
    }

    static void div_double(String val1, String val2){
        buffer += "%"+reg+" = fdiv double "+val2+", "+val1+"\n";
        reg++;
    }

    static void sitofp(String id){
        buffer += "%"+reg+" = sitofp i32 "+id+" to double\n";
        reg++;
    }

    static void fptosi(String id){
        buffer += "%"+reg+" = fptosi double "+id+" to i32\n";
        reg++;
    }
    static void call(String id, Integer function_type){
        if(function_type==1)
            buffer += "%"+reg+" = call i32 @"+id+"()\n";
        else if(function_type==2){
            buffer += "%"+reg+" = call double @"+id+"()\n";
        }
        reg++;
    }

    static void close_main(){
        main_text += buffer;
    }


    static String generate(){
        String text = "";
        text += "declare i32 @printf(i8*, ...)\n";
        text += "declare i32 @__isoc99_scanf(i8*, ...)\n";
        text += "@strpi = constant [4 x i8] c\"%d\\0A\\00\"\n";
        text += "@strpd = constant [4 x i8] c\"%f\\0A\\00\"\n";
        text += "@strs = constant [3 x i8] c\"%d\\00\"\n";
        text += header_text;
        text += "define i32 @main() nounwind{\n";
        text += main_text;
        text += "ret i32 0 }\n";
        return text;
    }

}