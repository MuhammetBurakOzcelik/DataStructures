
public class EvaluateString {

    private myStack_P2 operatorStack;
    private String[] postFix;

    /**
     * Constructor EvaluateString sets capacity of stack
     * @param capacity (Integer) capacity of postfix array
     */
    public EvaluateString (Integer capacity){
        postFix = new String[capacity];
    }

    /**
     * It allows you to understand which operator has priority to process. (Integer return)
     * @param symbol Incoming operator of Method. (String)
     * @return Returns precedence of operator.
     */
    public Integer precedence(String symbol)
    {
        if (symbol.equals("?(")){
            return 3;
        }
        else if(symbol.equals("#(")){
            return 3;
        }
        else if(symbol.equals("!(")){
            return 3;
        }
        else if(symbol.equals("*") || symbol.equals("/"))
        {
            return(2);
        }
        else if(symbol.equals("+") || symbol.equals("-"))
        {
            return(1);
        }
        else
        {
            return(0);
        }
    }

    /**
     * Allows us to understand whether the incoming string is an operator or variable.
     * @param symbol (String) Incoming operator or not
     * @return If operator returns 1 or 2, else returns 0.
     */
    public Integer is_operator(String symbol)
    {
        if(symbol.equals("*") || symbol.equals("/") || symbol.equals("+") || symbol.equals("-") )
        {
            return 1;
        }
        else if(symbol.equals("!(") || symbol.equals("?(") || symbol.equals("#("))
        {
            return 2;
        }
        else {
            return 0;
        }
    }

    /**
     * Infix is ​​used to convert the expression to postfix.
     * It pushes open brackets and operators into the stack. Operators push the stack by looking at the priorities
     * when disposing into the heap. Also variables append postfix array.When it sees closed brackets, it appends
     * the operators into the postfix array until he sees open brackets. Finally, it adds the values ​​of the names
     * that are the same as the variables read from the file. Then, Postfix is ready to calculate!
     * @param infix The infix expression read from the file.
     * @param val   Variable names and values ​​read from the file.
     * @param capacity  For stack to use allocate stack. (String length)
     */
    public void InfixToPostfix(String [] infix,String [] val, Integer capacity)
    {
        myStack_P2 newStack = new myStack_P2(capacity+1);
        newStack.push("(");
        String popOp;
        int counter = 0;

        for (int i = 0; i < infix.length; i++) {
            if(infix[i].equals("(")){
                newStack.push(infix[i]);
            }
            else if(is_operator(infix[i]) == 1 || is_operator(infix[i]) == 2){
                popOp = newStack.pop();

                while( (is_operator(popOp) == 1 || is_operator(popOp) == 2 ) &&
                        (precedence(popOp)>= precedence(infix[i])) )
                {
                    postFix[counter] = popOp;
                    popOp = newStack.pop();
                    counter++;
                }
                newStack.push(popOp);
                newStack.push(infix[i]);
            }
            else if(infix[i].equals(")")){
                popOp = newStack.pop();
                while(!(popOp.equals("("))){
                    postFix[counter] = popOp;
                    popOp = newStack.pop();
                    counter++;
                }
            }
            else if( is_operator(infix[i]) == 0){
                postFix[counter] = (infix[i]);
                counter++;
            }
        }
        while(!(newStack.peek().equals("("))){
            postFix[counter] = (newStack.pop());
            counter++;
        }

        System.out.print("PostFix --->\t: ");
        for (int i = 0; postFix[i] != null ; i++) {
            System.out.print(postFix[i]);
        }
        System.out.println();
        for (int i = 0; i < counter; i++) {
            for (int j = 0; j < val.length; j+=2) {
                if(postFix[i].equals(val[j])){
                    postFix[i] = postFix[i].replaceAll(postFix[i],val[j+1]);
                }
            }
        }
    }
    /**
     * The operator pushes the values ​​into the stack until the operator arrives. When the operator arrives, if the
     * operator is binary, pops the last two from the stack else if the operator is unary pop the last element from
     * stack and calculate the expression. Then it pushes stack. When the expression is completed, the popped element
     * will be the result.
     * @return  Result of infix to postfix expression. (Double)
     * @throws Exception    Can not Divide Zero
     */
    public double EvaluateResult() throws Exception {
        myStack_P2 resultStack = new myStack_P2(100);

        for (int i = 0; postFix[i] != null ; i++) {
            if(is_operator(postFix[i]) == 0){
                resultStack.push(postFix[i]);
            }
            else if(is_operator(postFix[i] ) == 1){
                double val1 = Double.parseDouble(resultStack.pop());
                double val2 = Double.parseDouble(resultStack.pop());

                if(postFix[i].equals("*")){
                    resultStack.push(String.valueOf((val2*val1)));
                }
                else if(postFix[i].equals("/")){
                    if((val1) == 0){
                        throw new Exception("Expression can cot divide zero! ");
                    }
                    resultStack.push(String.valueOf((val2/val1)));
                }
                else if(postFix[i].equals("-")){
                    resultStack.push(String.valueOf((val2-val1)));
                }
                else if(postFix[i].equals("+")){
                    resultStack.push(String.valueOf((val2+val1)));
                }
            }else if(is_operator(postFix[i]) == 2){
                double val1 = Double.parseDouble(resultStack.pop());
                if(postFix[i].equals("!(")){
                    resultStack.push(String.valueOf(sinCos(val1,0)));
                }
                else if(postFix[i].equals("?(") ){
                    resultStack.push(String.valueOf((sinCos(val1,1))));
                }
                else if(postFix[i].equals("#(")){
                    resultStack.push(String.valueOf((abs(val1))));
                }
            }
        }
        return Double.parseDouble(resultStack.pop());
    }

    /**
     * https://introcs.cs.princeton.edu/java/13flow/Sin.java.html
     * This solution help me for this issue. I edited for my problem.
     * @param degree x's value of cos(x)/sin(x).
     * @param control If control 1 this expression cos(x) else sin(x).
     * @return It returns cos or sin method's result.
     */
    public double sinCos (double degree, int control){
        degree = degree % 360;
        //Sinus complement with -90;
        if (control == 1) {
            degree = 90 - degree;
            if(degree < 0){
                degree += 360;
            }
        }
        // degree * radian
        double x = 0.0174533 * degree;
        // convert x to an angle between -2 PI and 2 PI
        x = x % (2 * 3.1415);

        if(degree % 180 == 0 && control == 0){
            return 0.0;
        }
        if(degree % 180 == 0 && control == 1){
            return 0.0;
        }
        // compute the Taylor series approximation
        double term = 1.0;      // ith term = x^i / i!
        double sum  = 0.0;      // sum of first i terms in taylor series

        for (int i = 1; term != 0.0; i++) {
            term *= (x / i);
            if (i % 4 == 1) sum += term;
            if (i % 4 == 3) sum -= term;
        }

        if(control == 0){
            return (sum);
        }
        if(control == 1){
            if(degree % 180 == 0 ){
                return 1.0;
            }else{
                return (sum);
            }
        }
        return 0;
    }

    /**
     * Returns the absolute value of the number.
     * @param v Variable (double)
     * @return Positive value of v.
     */
    public double abs (double v){
        if(v<0){
            return (v*(-1));
        }
        return v;
    }

    /**
     * It checks number of expression's parentheses and its valid. The brackets are correct
     * until the parentheses are closed. That's all.
     * @param text  Infix expression. (String)
     * @return  True or False.
     */
    public boolean isBalanced (String text){
        if(text == null){
            return false;
        }
        char [] test;
        myStack_P2 testStack = new myStack_P2(text.length());
        test = text.toCharArray();
        for (int i = 0; i <text.length() ; i++) {
            if(test[i] == '('){
                testStack.push("(");
            }
            else if(test[i] == ')'){
                if( testStack.isEmpty()){
                    return false;
                }else{
                    testStack.pop();
                }
            }
        }
        return testStack.isEmpty();
    }
}
