package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}


	/** Train the generator by adding the sourceText */


	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		String[] words = sourceText.split("\\s+");
		starter = words[0];
		String prevWord = starter;
		for (int idx =1; idx<words.length; idx++){
			String word = words[idx];
			if (indexOfWordNode(prevWord)==-1){
				ListNode listnode = new ListNode(prevWord);
				listnode.addNextWord(word);
				wordList.add(listnode);
			}
			else{
				int nodeIdx = indexOfWordNode(prevWord);
				wordList.get(nodeIdx).addNextWord(word);
			}
			prevWord = word;
		}
		String lastWord =words[words.length-1];
		if (indexOfWordNode(lastWord)!=-1){
			int nodeIdx = indexOfWordNode(lastWord);
			wordList.get(nodeIdx).addNextWord(starter);
		}
		else{
			ListNode lastnode = new ListNode(lastWord);
			lastnode.addNextWord(starter);
			wordList.add(lastnode);
		}

	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		if (numWords<=0 || wordList.size()==0){
			return "";
		}
		String currWord = wordList.get(0).getWord();
		String output = "";
		output += currWord;
		for (int num=1; num<numWords; num++){
			int nodeIdx = indexOfWordNode(currWord);
			ListNode listnode = wordList.get(nodeIdx);
			String word = listnode.getRandomNextWord(rnGenerator);
			output += " "+word;
            currWord = word;
		}
		return output;
	}


	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList.clear();
		train(sourceText);

	}

	// TODO: Add any private helper methods you need here.
	/** This is help method that find the index of Word Node in the wordList */
	private int indexOfWordNode(String word){
		for (int idx=0; idx<wordList.size(); idx++){
			ListNode listnode = wordList.get(idx);
			if (listnode.getWord().equals(word)){
				return idx;
			}
		}
		return -1;
	}

	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
//		String textString2 = "You say yes, I say no, "+
//				"You say stop, and I say go, go, go, "+
//				"Oh no. You say goodbye and I say hello, hello, hello, "+
//				"I don't know why you say goodbye, I say hello, hello, hello, "+
//				"I don't know why you say goodbye, I say hello. "+
//				"I say high, you say low, "+
//				"You say why, and I say I don't know. "+
//				"Oh no. "+
//				"You say goodbye and I say hello, hello, hello. "+
//				"I don't know why you say goodbye, I say hello, hello, hello, "+
//				"I don't know why you say goodbye, I say hello. "+
//				"Why, why, why, why, why, why, "+
//				"Do you say goodbye. "+
//				"Oh no. "+
//				"You say goodbye and I say hello, hello, hello. "+
//				"I don't know why you say goodbye, I say hello, hello, hello, "+
//				"I don't know why you say goodbye, I say hello. "+
//				"You say yes, I say no, "+
//				"You say stop and I say go, go, go. "+
//				"Oh, oh no. "+
//				"You say goodbye and I say hello, hello, hello. "+
//				"I don't know why you say goodbye, I say hello, hello, hello, "+
//				"I don't know why you say goodbye, I say hello, hello, hello, "+
//				"I don't know why you say goodbye, I say hello, hello, hello,";
//		System.out.println(textString2);
//		gen.retrain(textString2);
//		System.out.println(gen);
//		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from
	    // the MarkovTextGeneratorLoL class
		int length = nextWords.size();
		return nextWords.get(generator.nextInt(length));
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}


