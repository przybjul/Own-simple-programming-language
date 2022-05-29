// Generated from C:/Users/przyb/Desktop/Niedziela1/gen\Demo.g4 by ANTLR 4.10.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DemoLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, WRITE=8, TOINT=9, 
		TOREAL=10, READ=11, FUNCTION=12, ENDFUNCTION=13, LOOP=14, ENDLOOP=15, 
		REAL=16, IF=17, THEN=18, ENDIF=19, FREAL=20, FINT=21, ID=22, INT=23, ADD=24, 
		SUB=25, MULT=26, DIV=27, NEWLINE=28, WS=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "WRITE", "TOINT", 
			"TOREAL", "READ", "FUNCTION", "ENDFUNCTION", "LOOP", "ENDLOOP", "REAL", 
			"IF", "THEN", "ENDIF", "FREAL", "FINT", "ID", "INT", "ADD", "SUB", "MULT", 
			"DIV", "NEWLINE", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'('", "')'", "'=='", "'>'", "'<'", "'!='", "'write'", "'(int)'", 
			"'(real)'", "'read'", "'function'", "'endfunction'", "'loop'", "'endloop'", 
			null, "'if'", "'then'", "'endif'", "'real'", "'int'", null, null, "'+'", 
			"'-'", "'*'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "WRITE", "TOINT", "TOREAL", 
			"READ", "FUNCTION", "ENDFUNCTION", "LOOP", "ENDLOOP", "REAL", "IF", "THEN", 
			"ENDIF", "FREAL", "FINT", "ID", "INT", "ADD", "SUB", "MULT", "DIV", "NEWLINE", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public DemoLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Demo.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001d\u00c2\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0004\u000f\u0087\b\u000f\u000b\u000f\f\u000f\u0088\u0001"+
		"\u000f\u0001\u000f\u0004\u000f\u008d\b\u000f\u000b\u000f\f\u000f\u008e"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0004\u0015\u00a9\b\u0015\u000b\u0015\f\u0015\u00aa\u0001\u0016\u0004"+
		"\u0016\u00ae\b\u0016\u000b\u0016\f\u0016\u00af\u0001\u0017\u0001\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001b\u0003\u001b\u00bb\b\u001b\u0001\u001b\u0001\u001b\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0000\u0000\u001d\u0001\u0001\u0003"+
		"\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011"+
		"\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010"+
		"!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a"+
		"5\u001b7\u001c9\u001d\u0001\u0000\u0002\u0002\u0000AZaz\u0002\u0000\t"+
		"\t  \u00c6\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000"+
		"\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%"+
		"\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000"+
		"\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u0000"+
		"3\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001"+
		"\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0001;\u0001\u0000\u0000"+
		"\u0000\u0003=\u0001\u0000\u0000\u0000\u0005?\u0001\u0000\u0000\u0000\u0007"+
		"A\u0001\u0000\u0000\u0000\tD\u0001\u0000\u0000\u0000\u000bF\u0001\u0000"+
		"\u0000\u0000\rH\u0001\u0000\u0000\u0000\u000fK\u0001\u0000\u0000\u0000"+
		"\u0011Q\u0001\u0000\u0000\u0000\u0013W\u0001\u0000\u0000\u0000\u0015^"+
		"\u0001\u0000\u0000\u0000\u0017c\u0001\u0000\u0000\u0000\u0019l\u0001\u0000"+
		"\u0000\u0000\u001bx\u0001\u0000\u0000\u0000\u001d}\u0001\u0000\u0000\u0000"+
		"\u001f\u0086\u0001\u0000\u0000\u0000!\u0090\u0001\u0000\u0000\u0000#\u0093"+
		"\u0001\u0000\u0000\u0000%\u0098\u0001\u0000\u0000\u0000\'\u009e\u0001"+
		"\u0000\u0000\u0000)\u00a3\u0001\u0000\u0000\u0000+\u00a8\u0001\u0000\u0000"+
		"\u0000-\u00ad\u0001\u0000\u0000\u0000/\u00b1\u0001\u0000\u0000\u00001"+
		"\u00b3\u0001\u0000\u0000\u00003\u00b5\u0001\u0000\u0000\u00005\u00b7\u0001"+
		"\u0000\u0000\u00007\u00ba\u0001\u0000\u0000\u00009\u00be\u0001\u0000\u0000"+
		"\u0000;<\u0005=\u0000\u0000<\u0002\u0001\u0000\u0000\u0000=>\u0005(\u0000"+
		"\u0000>\u0004\u0001\u0000\u0000\u0000?@\u0005)\u0000\u0000@\u0006\u0001"+
		"\u0000\u0000\u0000AB\u0005=\u0000\u0000BC\u0005=\u0000\u0000C\b\u0001"+
		"\u0000\u0000\u0000DE\u0005>\u0000\u0000E\n\u0001\u0000\u0000\u0000FG\u0005"+
		"<\u0000\u0000G\f\u0001\u0000\u0000\u0000HI\u0005!\u0000\u0000IJ\u0005"+
		"=\u0000\u0000J\u000e\u0001\u0000\u0000\u0000KL\u0005w\u0000\u0000LM\u0005"+
		"r\u0000\u0000MN\u0005i\u0000\u0000NO\u0005t\u0000\u0000OP\u0005e\u0000"+
		"\u0000P\u0010\u0001\u0000\u0000\u0000QR\u0005(\u0000\u0000RS\u0005i\u0000"+
		"\u0000ST\u0005n\u0000\u0000TU\u0005t\u0000\u0000UV\u0005)\u0000\u0000"+
		"V\u0012\u0001\u0000\u0000\u0000WX\u0005(\u0000\u0000XY\u0005r\u0000\u0000"+
		"YZ\u0005e\u0000\u0000Z[\u0005a\u0000\u0000[\\\u0005l\u0000\u0000\\]\u0005"+
		")\u0000\u0000]\u0014\u0001\u0000\u0000\u0000^_\u0005r\u0000\u0000_`\u0005"+
		"e\u0000\u0000`a\u0005a\u0000\u0000ab\u0005d\u0000\u0000b\u0016\u0001\u0000"+
		"\u0000\u0000cd\u0005f\u0000\u0000de\u0005u\u0000\u0000ef\u0005n\u0000"+
		"\u0000fg\u0005c\u0000\u0000gh\u0005t\u0000\u0000hi\u0005i\u0000\u0000"+
		"ij\u0005o\u0000\u0000jk\u0005n\u0000\u0000k\u0018\u0001\u0000\u0000\u0000"+
		"lm\u0005e\u0000\u0000mn\u0005n\u0000\u0000no\u0005d\u0000\u0000op\u0005"+
		"f\u0000\u0000pq\u0005u\u0000\u0000qr\u0005n\u0000\u0000rs\u0005c\u0000"+
		"\u0000st\u0005t\u0000\u0000tu\u0005i\u0000\u0000uv\u0005o\u0000\u0000"+
		"vw\u0005n\u0000\u0000w\u001a\u0001\u0000\u0000\u0000xy\u0005l\u0000\u0000"+
		"yz\u0005o\u0000\u0000z{\u0005o\u0000\u0000{|\u0005p\u0000\u0000|\u001c"+
		"\u0001\u0000\u0000\u0000}~\u0005e\u0000\u0000~\u007f\u0005n\u0000\u0000"+
		"\u007f\u0080\u0005d\u0000\u0000\u0080\u0081\u0005l\u0000\u0000\u0081\u0082"+
		"\u0005o\u0000\u0000\u0082\u0083\u0005o\u0000\u0000\u0083\u0084\u0005p"+
		"\u0000\u0000\u0084\u001e\u0001\u0000\u0000\u0000\u0085\u0087\u000209\u0000"+
		"\u0086\u0085\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000"+
		"\u0088\u0086\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000"+
		"\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u008c\u0005.\u0000\u0000\u008b"+
		"\u008d\u000209\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008d\u008e\u0001"+
		"\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f\u0001"+
		"\u0000\u0000\u0000\u008f \u0001\u0000\u0000\u0000\u0090\u0091\u0005i\u0000"+
		"\u0000\u0091\u0092\u0005f\u0000\u0000\u0092\"\u0001\u0000\u0000\u0000"+
		"\u0093\u0094\u0005t\u0000\u0000\u0094\u0095\u0005h\u0000\u0000\u0095\u0096"+
		"\u0005e\u0000\u0000\u0096\u0097\u0005n\u0000\u0000\u0097$\u0001\u0000"+
		"\u0000\u0000\u0098\u0099\u0005e\u0000\u0000\u0099\u009a\u0005n\u0000\u0000"+
		"\u009a\u009b\u0005d\u0000\u0000\u009b\u009c\u0005i\u0000\u0000\u009c\u009d"+
		"\u0005f\u0000\u0000\u009d&\u0001\u0000\u0000\u0000\u009e\u009f\u0005r"+
		"\u0000\u0000\u009f\u00a0\u0005e\u0000\u0000\u00a0\u00a1\u0005a\u0000\u0000"+
		"\u00a1\u00a2\u0005l\u0000\u0000\u00a2(\u0001\u0000\u0000\u0000\u00a3\u00a4"+
		"\u0005i\u0000\u0000\u00a4\u00a5\u0005n\u0000\u0000\u00a5\u00a6\u0005t"+
		"\u0000\u0000\u00a6*\u0001\u0000\u0000\u0000\u00a7\u00a9\u0007\u0000\u0000"+
		"\u0000\u00a8\u00a7\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000"+
		"\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000"+
		"\u0000\u00ab,\u0001\u0000\u0000\u0000\u00ac\u00ae\u000209\u0000\u00ad"+
		"\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af"+
		"\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0"+
		".\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005+\u0000\u0000\u00b20\u0001"+
		"\u0000\u0000\u0000\u00b3\u00b4\u0005-\u0000\u0000\u00b42\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b6\u0005*\u0000\u0000\u00b64\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b8\u0005/\u0000\u0000\u00b86\u0001\u0000\u0000\u0000\u00b9\u00bb\u0005"+
		"\r\u0000\u0000\u00ba\u00b9\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000"+
		"\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00bd\u0005\n\u0000"+
		"\u0000\u00bd8\u0001\u0000\u0000\u0000\u00be\u00bf\u0007\u0001\u0000\u0000"+
		"\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c1\u0006\u001c\u0000\u0000"+
		"\u00c1:\u0001\u0000\u0000\u0000\u0006\u0000\u0088\u008e\u00aa\u00af\u00ba"+
		"\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}