/************************************************
 * Source code information
 * -----------------------
 * Original author	 Peng Wang, School of Computer Science & Eng., Southeast University
 * Author email      pwangseu@gmail.com
 * Web               http://ontomapping.googlepages.com
 * Created			 2007-5-13
 * Filename          DelStopWords.java
 * Version           2.0
 *
 * Last modified on  2007-5-13
 *               by  Peng Wang
 * -----------------------
 * Functions describe:
 * 删除文本中的Stop words
 ***********************************************/
package lily.tool.textprocess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

/*********************
 * Class information
 * -------------------
 * @author Peng Wang
 * @date   2007-5-13
 *
 * describe:
 * 去除频繁词
 * 提供了3个频繁词集合供选择
 ********************/
public class DelStopWords {
	public Set StopWordSet=new HashSet();

	public  ArrayList removeStopWords(ArrayList wordList)
	{
		ArrayList list=new ArrayList();
		for (Iterator it=wordList.iterator();it.hasNext();){
			String s=(String)it.next();
			if (!StopWordSet.contains(s.toLowerCase())){
				list.add(s);
			}
		}
		return list;
	}

	public  String removeStopWords(String source)
	{
//		return source;

        /* 剔除. */
		int positionOfDot;
		StringBuffer tempS = new StringBuffer(source);
		while ((positionOfDot = tempS.indexOf(".")) != -1) {
			tempS.deleteCharAt(positionOfDot);
		}
		source = tempS.toString();

		//分隔符
		String delimiters = " \t\n\r\f~!@#$%^&*()_+|`-=\\{}[]:\";<>?,.";
		 /* 根据分隔符分词 */
		StringTokenizer stringTokenizer = new StringTokenizer(source,
				delimiters);

		//判断是否Stop Words
		StringBuffer tempSource = new StringBuffer();
		while (stringTokenizer.hasMoreTokens()) {
			String token = stringTokenizer.nextToken();
			if (!isStropWord(token.toLowerCase())){
				tempSource.append(token+" ");
			}
		}
		return tempSource.toString();
	}

	public boolean isStropWord(String inStr)
	{
		return (StopWordSet.contains(inStr));
	}

	public void loadStopWords()
	{
		// setWord1();
		//setWord2();
		setWord3();
	}

	public int setWord1()
	{
		int num=648;
		String[] words={
				"a",		"able",		"about",	"above",	"abroad",	"according",	"accordingly",	"across",
				"actually",	"adj",		"after",	"afterwards",	"again",	"against",	"ago",		"ahead",
				"ain't",	"all",		"allow",	"allows",	"almost",	"alone",	"along",	"alongside",
				"already",	"also",		"although",	"always",	"am",		"amid",		"amidst",	"among",
				"amongst",	"an",		"and",		"another",	"any",		"anybody",	"anyhow",	"anyone",
				"anything",	"anyway",	"anyways",	"anywhere",	"apart",	"appear",	"appreciate",	"appropriate",
				"are",		"aren't",	"around",	"as",		"a's",		"aside",
				"associated",	"at",		"available",	"away",		"awfully",	"b",		"back",		"backward",
				"backwards",	"be",		"became",	"because",	"become",	"becomes",	"becoming",	"been",
				"before",	"beforehand",	"begin",	"behind",	"being",	"believe",	"below",	"beside",
				"besides",	"best",		"better",	"between",	"beyond",	"both",		"but",
				"by",		"c",		"came",		"can",		"cannot",	"cant",		"can't",	"caption",
				"cause",	"causes",	"certain",	"certainly",	"changes",	"clearly",	"c'mon",	"co",
				"co.",		"com",		"come",		"comes",	"concerning",	"consequently",	"consider",	"considering",
				"contain",	"containing",	"contains",	"corresponding","could",	"couldn't",	"course",	"c's",
				"currently",	"d",		"dare",		"daren't",	"definitely",	"described",	"despite",	"did",
				"didn't",	"different",	"directly",	"do",		"does",		"doesn't",	"doing",	"done",
				"don't",	"down",		"downwards",	"during",	"e",		"each",		"eg",
				"eight",	"eighty",	"either",	"else",		"elsewhere",	"end",		"ending",	"enough",
				"entirely",	"especially",	"et",		"etc",		"even",		"ever",		"evermore",	"every",
				"everybody",	"everyone",	"everything",	"everywhere",	"ex",		"exactly",	"except",
				"f",		"fairly",	"far",		"few",		"fewer",	"fifth",	"first",
				"five",		"followed",	"following",	"follows",	"for",		"forever",	"former",	"formerly",
				"forth",	"forward",	"found",	"four",		"from",		"further",	"furthermore",	"g",
				"get",		"gets",		"getting",	"given",	"gives",	"go",		"goes",		"going",
				"gone",		"got",		"gotten",	"greetings",	"h",		"had",		"hadn't",	"half",
				"happens",	"hardly",	"has",		"hasn't",	"have",		"haven't",	"having",	"he",
				"he'd",		"he'll",	"hello",	"help",		"hence",	"her",		"here",		"hereafter",
				"hereby",	"herein",	"here's",	"hereupon",	"hers",		"herself",	"he's",		"hi",
				"him",		"himself",	"his",		"hither",	"hopefully",	"how",		"howbeit",	"however",
				"hundred",	"i",		"i'd",		"ie",		"if",		"ignored",	"i'll",		"i'm",
				"immediate",	"in",		"inasmuch",	"inc",		"inc.",		"indeed",	"indicate",	"indicated",
				"indicates",	"inner",	"inside",	"insofar",	"instead",	"into",		"inward",	"is",
				"isn't",	"it",		"it'd",		"it'll",	"its",		"it's",		"itself",	"i've",
				"j",		"just",		"k",		"keep",		"keeps",	"kept",		"know",		"known",
				"knows",	"l",		"last",		"lately",	"later",	"latter",	"latterly",	"least",
				"less",		"lest",		"let",		"let's",	"like",		"liked",	"likely",	"likewise",
				"little",	"look",		"looking",	"looks",	"low",		"lower",	"ltd",		"m",
				"mainly",	"make",		"makes",	"many",		"may",		"maybe",	"mayn't",
				"me",		"mean",		"meantime",	"meanwhile",	"merely",	"might",	"mightn't",	"mine",
				"minus",	"miss",		"more",		"moreover",	"most",		"mostly",	"mr",		"mrs",
				"much",		"must",		"mustn't",	"my",		"myself",	"n",		"namely",
				"nd",		"near",		"nearly",	"necessary",	"need",		"needn't",	"needs",	"neither",
				"never",	"neverf",	"neverless",	"nevertheless",	"new",		"next",		"nine",		"ninety",
				"no",		"nobody",	"non",		"none",		"nonetheless",	"noone",	"no-one",	"nor",
				"normally",	"not",		"nothing",	"notwithstanding","novel",	"now",		"nowhere",	"o",
				"obviously",	"of",		"off",		"often",	"oh",		"ok",		"okay",		"old",
				"on",		"once",		"one",		"ones",		"one's",	"only",		"onto",		"opposite",
				"or",		"other",	"others",	"otherwise",	"ought",	"oughtn't",	"our",		"ours",
				"ourselves",	"out",		"outside",	"over",		"overall",	"own",	"p",	"particular",
				"particularly",	"past",		"per",		"perhaps",	"placed",	"please",	"plus",		"possible",
				"presumably",	"probably",	"q",		"que",		"quite",	"qv",
				"r",		"rather",	"rd",		"re",		"really",	"reasonably",	"recent",	"recently",
				"regarding",	"regardless",	"regards",	"relatively",	"respectively",	"right",	"round",	"s",
				"said",		"same",		"saw",		"say",		"saying",	"says",		"second",	"secondly",
				"see",		"seeing",	"seem",		"seemed",	"seeming",	"seems",	"seen",		"self",
				"selves",	"sensible",	"sent",		"serious",	"seriously",	"seven",	"several",	"shall",
				"shan't",	"she",		"she'd",	"she'll",	"she's",	"should",	"shouldn't",	"since",
				"six",		"so",		"some",		"somebody",	"someday",	"somehow",	"someone",	"something",
				"sometime",	"sometimes",	"somewhat",	"somewhere",	"soon",		"sorry",	"specified",	"specify",
				"specifying",	"still",	"sub",		"such",		"sup",		"sure",		"t",		"take",
				"taken",	"taking",	"tell",		"tends",	"th",		"than",		"thank",	"thanks",
				"thanx",	"that",		"that'll",	"thats",	"that's",	"that've",	"the",		"their",
				"theirs",	"them",		"themselves",	"then",		"thence",	"there",	"thereafter",	"thereby",
				"there'd",	"therefore",	"therein",	"there'll",	"there're",	"theres",	"there's",	"thereupon",
				"there've",	"these",	"they",		"they'd",	"they'll",	"they're",	"they've",	"thing",
				"things",	"think",	"third",	"thirty",	"this",		"thorough",	"thoroughly",	"those",
				"though",	"three",	"through",	"throughout",	"thru",		"thus",		"till",	"to",
				"together",	"too",		"took",		"toward",	"towards",	"tried",	"tries",	"truly",
				"try",		"trying",	"t's",		"twice",	"two",		"u",		"un",		"under",
				"underneath",	"undoing",	"unfortunately","unless",	"unlike",	"unlikely",	"until",	"unto",
				"up",		"upon",		"upwards",	"us",		"use",		"used",		"useful",	"uses",
				"using",	"usually",	"v",		"value",	"various",	"versus",	"very",		"via",
				"viz",		"vs",		"w",		"want",		"wants",	"was",		"wasn't",	"way",
				"we",		"we'd",		"welcome",	"well",		"we'll",	"went",		"were",		"we're",
				"weren't",	"we've",	"what",		"whatever",	"what'll",	"what's",	"what've",	"when",
				"whence",	"whenever",	"where",	"whereafter",	"whereas",	"whereby",	"wherein",	"where's",
				"whereupon",	"wherever",	"whether",	"which",	"whichever",	"while",	"whilst",	"whither",
				"who",		"who'd",	"whoever",	"whole",	"who'll",	"whom",		"whomever",	"who's",
				"whose",	"why",		"will",		"willing",	"wish",		"with",		"within",	"without",
				"wonder",	"won't",	"would",	"wouldn't",	"x",		"y",		"yes",		"yet",
				"you",		"you'd",	"you'll",	"your",		"you're",	"yours",	"yourself",	"yourselves",
				"you've",	"z"};

		for (int i=0;i<num;i++)
		{
			StopWordSet.add(words[i]);
		}
		return num;

	}
	public int setWord2()
	{
		int num=262;
		String[] words={
				"a",		"about",	"above",	"afterwards",	"again",	"against",	"all",
				"almost",	"along",	"already",	"also",		"although",	"always",	"am",		"among",
				"amongst",	"amoungst",	"amount",	"an",		"and",		"another",	"any",		"anyhow",
				"anyone",	"anything",	"anyway",	"anywhere",	"are",		"around",	"as",		"at",
				"back",		"be",		"became",	"because",	"become",	"becomes",	"becoming",	"been",
				"before",	"beforehand",	"behind",	"being",	"below",	"beside",	"besides",	"between",
				"beyond",	"both",		"but",		"by",		"can",		"cannot",	"cant",		"co",
				"con",		"could",	"couldnt",	"de",		"do",		"done",		"due",		"during",
				"each",		"eg",		"either",	"else",		"elsewhere",	"enough",	"etc",		"even",
				"ever",		"every",	"everyone",	"everything",	"everywhere",	"except",	"few",
				"for",		"formerly",	"from",		"further",	"had",		"hasnt",
				"he",		"hence",	"her",		"here",		"hereafter",	"hereby",	"herein",	"hereupon",
				"hers",		"herself",	"him",		"himself",	"his",		"however",	"hundred",
				"i",		"ie",		"if",		"inc",		"indeed",	"interest",	"into",
				"is",		"it",		"its",		"itself",	"last",		"latter",	"latterly",	"least",
				"less",		"ltd",		"many",		"may",		"me",		"meanwhile",	"might",	"mine",
				"more",		"moreover",	"most",		"mostly",	"much",		"must",		"my",		"myself",
				"namely",	"neither",	"never",	"nevertheless",	"next",		"no",		"nobody",	"none",
				"noone",	"nor",		"not",		"nothing",	"now",		"nowhere",	"of",		"off",
				"often",	"on",		"once",		"only",		"onto",		"or",		"other",	"others",
				"otherwise",	"our",		"ours",		"ourselves",	"out",		"over",		"own",		"per",
				"perhaps",	"please",	"rather",	"re",		"see",		"seem",		"seemed",	"seeming",
				"seems",	"several",	"she",		"should",	"side",		"since",	"sincere",	"so",
				"some",		"somehow",	"someone",	"something",	"sometime",	"sometimes",	"somewhere",	"still",
				"such",		"than",		"that",		"the",		"their",	"them",		"themselves",	"then",
				"thence",	"there",	"thereafter",	"thereby",	"therefore",	"therein",	"thereupon",	"these",
				"they",		"this",		"those",	"though",	"three",	"through",	"throughout",	"thru",
				"thus",		"to",		"together",	"too",		"top",		"toward",	"towards",	"un",
				"under",	"until",	"up",		"upon",		"us",		"very",		"via",		"was",
				"we",		"well",		"were",		"what",		"whatever",	"when",		"whence",	"whenever",
				"where",	"whereafter",	"whereas",	"whereby",	"wherein",	"whereupon",	"wherever",	"whether",
				"which",	"while",	"whither",	"who",		"whoever",	"whole",	"whom",		"whose",
				"why",		"will",		"with",		"within",	"without",	"would",	"yet",		"you",
				"your",		"yours",	"yourself",	"yourselves"};
		for (int i=0;i<num;i++)
		{
			StopWordSet.add(words[i]);
		}
		return num;

	}
	public int setWord3()
	{
		int num=429;
		String[] words={
				"a",		"about",	"above",	"across",	"after",	"again",	"against",	"all",
				"almost",	"alone",	"along",	"already",	"also",		"although",	"always",	"among",
				"an",		"and",		"another",	"any",		"anybody",	"anyone",	"anything",	"anywhere",
				"are",		"area",		"areas",	"around",	"as",		"ask",		"asked",	"asking",
				"asks",		"at",		"away",	"b",	"back",		"backed",	"backing",	"backs",
				"be",		"became",	"because",	"become",	"becomes",	"been",		"before",	"began",
				"behind",	"being",	"beings",	"best",		"better",	"between",	"big",		"both",
				"but",		"by",		"c",		"came",		"can",		"cannot",	"case",		"cases",
				"certain",	"certainly",	"clear",	"clearly",	"come",		"could",	"d",		"did",
				"differ",	"different",	"differently",	"do",		"does",		"done",		"down",		"down",
				"downed",	"downing",	"downs",	"during",	"e",		"each",		"early",	"either",
				"end",		"ended",	"ending",	"ends",		"enough",	"even",		"evenly",	"ever",
				"every",	"everybody",	"everyone",	"everything",	"everywhere",	"f",		"face",		"faces",
				"fact",		"facts",	"far",		"felt",		"few",	"	find",		"finds",	"first",
				"for",		"four",		"from",		"full",		"fully",	"further",	"furthered",	"furthering",
				"furthers",	"g",		"gave",		"general",	"generally",	"get",		"gets",		"give",
				"given",	"gives",	"go",		"going",	"good",		"goods",	"got",		"great",
				"greater",	"greatest",	"group",	"grouped",	"grouping",	"groups",	"h",		"had",
				"has",		"have",		"having",	"he",		"her",		"here",		"herself",	"high",
				"high",		"high",		"higher",	"highest",	"him",		"himself",	"his",		"how",
				"however",	"i",		"if",		"important",	"in",		"interest",	"interested",	"interesting",
				"interests",	"into",		"is",		"it",		"its",		"itself",	"j",		"just",
				"k",		"keep",		"keeps",	"kind",		"knew",		"know",		"known",	"knows",
				"l",		"large",	"largely",	"last",		"later",	"latest",	"least",	"less",
				"let",		"lets",		"like",		"likely",	"long",		"longer",	"longest",	"m",
				"made",		"make",		"making",	"man",		"many",		"may",		"me",		"member",
				"members",	"men",		"might",	"more",		"most",		"mostly",	"mr",		"mrs",
				"much",		"must",		"my",		"myself",	"n",		"necessary",	"need",		"needed",
				"needing",	"needs",	"never",	"new",		"new",		"newer",	"newest",	"next",
				"no",		"nobody",	"non",		"noone",	"not",		"nothing",	"now",		"nowhere",
				"number",	"numbers",	"o",		"of",		"off",		"often",	"old",		"older",
				"oldest",	"on",		"once",		"one",		"only",		"open",		"opened",	"opening",
				"opens",	"or",		"order",	"ordered",	"ordering",	"orders",	"other",	"others",
				"our",		"out",		"over",		"p",		"part",		"parted",	"parting",	"parts",
				"per",		"perhaps",	"place",	"places",	"point",	"pointed",	"pointing",	"points",
				"possible",	"present",	"presented",	"presenting",	"presents",	"problem",	"problems",	"put",
				"puts",		"q",		"quite",	"r",		"rather",	"really",	"right",	"right",
				"room",		"rooms",	"s",		"said",		"same",		"saw",		"say",		"says",
				"second",	"seconds",	"see",		"seem",		"seemed",	"seeming",	"seems",	"sees",
				"several",	"shall",	"she",		"should",	"show",		"showed",	"showing",	"shows",
				"side",		"sides",	"since",	"small",	"smaller",	"smallest",	"so",		"some",
				"somebody",	"someone",	"something",	"somewhere",	"state",	"states",	"still",	"still",
				"such",		"sure",		"t",		"take",		"taken",	"than",		"that",		"the",
				"their",	"them",		"then",		"there",	"therefore",	"these",	"they",		"thing",
				"things",	"think",	"thinks",	"this",		"those",	"though",	"thought",	"thoughts",
				"three",	"through",	"thus",		"to",		"today",	"together",	"too",		"took",
				"toward",	"turn",		"turned",	"turning",	"turns",	"two",		"u",		"under",
				"until",	"up",		"upon",		"us",		"use",		"used",		"uses",		"v",
				"very",		"w",		"want",		"wanted",	"wanting",	"wants",	"was",		"way",
				"ways",		"we",		"well",		"wells",	"went",		"were",		"what",		"when",
				"where",	"whether",	"which",	"while",	"who",		"whole",	"whose",	"why",
				"will",		"with",		"within",	"without",	"work",		"worked",	"working",	"works",
				"would",	"x",		"y",		"year",		"years",	"yet",		"you",		"young",
				"younger",	"youngest",	"your",		"yours",	"z"
		};
		for (int i=0;i<num;i++)
		{
			StopWordSet.add(words[i]);
		}
		return num;

	}
}
