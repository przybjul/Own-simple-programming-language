# Own-simple-programming-language
Design of a very simple programming language and implementation of its compiler. The project covers all stages of source code processing, up to the creation of machine code. Tools supporting the work of the compiler developer were used.  The front of the compiler, i.e. the lexical syntax analysis, was performed using the ANTLR analyzer generator. An intermediate representation (IR) should be generated from the resulting AST tree in a form compliant with the LLVM specification. Indirect representation optimization as well as machine code generation will be performed by a tool available in LLVM.

# Realization
The program was made in Java 17.0.3, lexical parsing was done by ANTLR 4.10.1. The most important files are: 'LLVMActions' it contains the code supporting the created language, while the 'LLVMGenerator' file is responsible for the intermediate code (IR) generation.

# Characteristics of the language
1. Simple arithmetic operations
2. Conditional statements
3. Loops
4. Functions
5. Global and local variables
