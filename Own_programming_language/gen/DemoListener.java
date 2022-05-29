// Generated from C:/Users/przyb/Desktop/Niedziela1/gen\Demo.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DemoParser}.
 */
public interface DemoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DemoParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(DemoParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link DemoParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(DemoParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link DemoParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(DemoParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link DemoParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(DemoParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIf(DemoParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIf(DemoParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code repeat}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterRepeat(DemoParser.RepeatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code repeat}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitRepeat(DemoParser.RepeatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code write}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWrite(DemoParser.WriteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code write}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWrite(DemoParser.WriteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssign(DemoParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssign(DemoParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code read}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterRead(DemoParser.ReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code read}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitRead(DemoParser.ReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code single0}
	 * labeled alternative in {@link DemoParser#expr0}.
	 * @param ctx the parse tree
	 */
	void enterSingle0(DemoParser.Single0Context ctx);
	/**
	 * Exit a parse tree produced by the {@code single0}
	 * labeled alternative in {@link DemoParser#expr0}.
	 * @param ctx the parse tree
	 */
	void exitSingle0(DemoParser.Single0Context ctx);
	/**
	 * Enter a parse tree produced by the {@code add}
	 * labeled alternative in {@link DemoParser#expr0}.
	 * @param ctx the parse tree
	 */
	void enterAdd(DemoParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code add}
	 * labeled alternative in {@link DemoParser#expr0}.
	 * @param ctx the parse tree
	 */
	void exitAdd(DemoParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sub}
	 * labeled alternative in {@link DemoParser#expr0}.
	 * @param ctx the parse tree
	 */
	void enterSub(DemoParser.SubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link DemoParser#expr0}.
	 * @param ctx the parse tree
	 */
	void exitSub(DemoParser.SubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code single1}
	 * labeled alternative in {@link DemoParser#expr1}.
	 * @param ctx the parse tree
	 */
	void enterSingle1(DemoParser.Single1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code single1}
	 * labeled alternative in {@link DemoParser#expr1}.
	 * @param ctx the parse tree
	 */
	void exitSingle1(DemoParser.Single1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code mult}
	 * labeled alternative in {@link DemoParser#expr1}.
	 * @param ctx the parse tree
	 */
	void enterMult(DemoParser.MultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mult}
	 * labeled alternative in {@link DemoParser#expr1}.
	 * @param ctx the parse tree
	 */
	void exitMult(DemoParser.MultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code div}
	 * labeled alternative in {@link DemoParser#expr1}.
	 * @param ctx the parse tree
	 */
	void enterDiv(DemoParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code div}
	 * labeled alternative in {@link DemoParser#expr1}.
	 * @param ctx the parse tree
	 */
	void exitDiv(DemoParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 */
	void enterInt(DemoParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 */
	void exitInt(DemoParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code real}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 */
	void enterReal(DemoParser.RealContext ctx);
	/**
	 * Exit a parse tree produced by the {@code real}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 */
	void exitReal(DemoParser.RealContext ctx);
	/**
	 * Enter a parse tree produced by the {@code toint}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 */
	void enterToint(DemoParser.TointContext ctx);
	/**
	 * Exit a parse tree produced by the {@code toint}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 */
	void exitToint(DemoParser.TointContext ctx);
	/**
	 * Enter a parse tree produced by the {@code toreal}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 */
	void enterToreal(DemoParser.TorealContext ctx);
	/**
	 * Exit a parse tree produced by the {@code toreal}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 */
	void exitToreal(DemoParser.TorealContext ctx);
	/**
	 * Enter a parse tree produced by the {@code par}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 */
	void enterPar(DemoParser.ParContext ctx);
	/**
	 * Exit a parse tree produced by the {@code par}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 */
	void exitPar(DemoParser.ParContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 */
	void enterId(DemoParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 */
	void exitId(DemoParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link DemoParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(DemoParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DemoParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(DemoParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code freal}
	 * labeled alternative in {@link DemoParser#type}.
	 * @param ctx the parse tree
	 */
	void enterFreal(DemoParser.FrealContext ctx);
	/**
	 * Exit a parse tree produced by the {@code freal}
	 * labeled alternative in {@link DemoParser#type}.
	 * @param ctx the parse tree
	 */
	void exitFreal(DemoParser.FrealContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fint}
	 * labeled alternative in {@link DemoParser#type}.
	 * @param ctx the parse tree
	 */
	void enterFint(DemoParser.FintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fint}
	 * labeled alternative in {@link DemoParser#type}.
	 * @param ctx the parse tree
	 */
	void exitFint(DemoParser.FintContext ctx);
	/**
	 * Enter a parse tree produced by {@link DemoParser#fparam}.
	 * @param ctx the parse tree
	 */
	void enterFparam(DemoParser.FparamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DemoParser#fparam}.
	 * @param ctx the parse tree
	 */
	void exitFparam(DemoParser.FparamContext ctx);
	/**
	 * Enter a parse tree produced by {@link DemoParser#fblock}.
	 * @param ctx the parse tree
	 */
	void enterFblock(DemoParser.FblockContext ctx);
	/**
	 * Exit a parse tree produced by {@link DemoParser#fblock}.
	 * @param ctx the parse tree
	 */
	void exitFblock(DemoParser.FblockContext ctx);
	/**
	 * Enter a parse tree produced by {@link DemoParser#blockif}.
	 * @param ctx the parse tree
	 */
	void enterBlockif(DemoParser.BlockifContext ctx);
	/**
	 * Exit a parse tree produced by {@link DemoParser#blockif}.
	 * @param ctx the parse tree
	 */
	void exitBlockif(DemoParser.BlockifContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equal}
	 * labeled alternative in {@link DemoParser#sign}.
	 * @param ctx the parse tree
	 */
	void enterEqual(DemoParser.EqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equal}
	 * labeled alternative in {@link DemoParser#sign}.
	 * @param ctx the parse tree
	 */
	void exitEqual(DemoParser.EqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code greater}
	 * labeled alternative in {@link DemoParser#sign}.
	 * @param ctx the parse tree
	 */
	void enterGreater(DemoParser.GreaterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code greater}
	 * labeled alternative in {@link DemoParser#sign}.
	 * @param ctx the parse tree
	 */
	void exitGreater(DemoParser.GreaterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lower}
	 * labeled alternative in {@link DemoParser#sign}.
	 * @param ctx the parse tree
	 */
	void enterLower(DemoParser.LowerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lower}
	 * labeled alternative in {@link DemoParser#sign}.
	 * @param ctx the parse tree
	 */
	void exitLower(DemoParser.LowerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negative}
	 * labeled alternative in {@link DemoParser#sign}.
	 * @param ctx the parse tree
	 */
	void enterNegative(DemoParser.NegativeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negative}
	 * labeled alternative in {@link DemoParser#sign}.
	 * @param ctx the parse tree
	 */
	void exitNegative(DemoParser.NegativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DemoParser#repetitions}.
	 * @param ctx the parse tree
	 */
	void enterRepetitions(DemoParser.RepetitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DemoParser#repetitions}.
	 * @param ctx the parse tree
	 */
	void exitRepetitions(DemoParser.RepetitionsContext ctx);
}