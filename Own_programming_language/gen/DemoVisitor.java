// Generated from C:/Users/przyb/Desktop/Niedziela1/gen\Demo.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DemoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DemoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DemoParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(DemoParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(DemoParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(DemoParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code repeat}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeat(DemoParser.RepeatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code write}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrite(DemoParser.WriteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(DemoParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code read}
	 * labeled alternative in {@link DemoParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead(DemoParser.ReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code single0}
	 * labeled alternative in {@link DemoParser#expr0}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle0(DemoParser.Single0Context ctx);
	/**
	 * Visit a parse tree produced by the {@code add}
	 * labeled alternative in {@link DemoParser#expr0}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(DemoParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link DemoParser#expr0}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(DemoParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code single1}
	 * labeled alternative in {@link DemoParser#expr1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle1(DemoParser.Single1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code mult}
	 * labeled alternative in {@link DemoParser#expr1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(DemoParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code div}
	 * labeled alternative in {@link DemoParser#expr1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(DemoParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(DemoParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code real}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReal(DemoParser.RealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code toint}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToint(DemoParser.TointContext ctx);
	/**
	 * Visit a parse tree produced by the {@code toreal}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToreal(DemoParser.TorealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code par}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPar(DemoParser.ParContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link DemoParser#expr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(DemoParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(DemoParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code freal}
	 * labeled alternative in {@link DemoParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFreal(DemoParser.FrealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fint}
	 * labeled alternative in {@link DemoParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFint(DemoParser.FintContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoParser#fparam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFparam(DemoParser.FparamContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoParser#fblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFblock(DemoParser.FblockContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoParser#blockif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockif(DemoParser.BlockifContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equal}
	 * labeled alternative in {@link DemoParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqual(DemoParser.EqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code greater}
	 * labeled alternative in {@link DemoParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreater(DemoParser.GreaterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lower}
	 * labeled alternative in {@link DemoParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLower(DemoParser.LowerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negative}
	 * labeled alternative in {@link DemoParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegative(DemoParser.NegativeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoParser#repetitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepetitions(DemoParser.RepetitionsContext ctx);
}