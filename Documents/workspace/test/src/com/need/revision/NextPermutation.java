package com.need.revision;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NextPermutation {
  public static <T> boolean equal(List<T> list1, List<T> list2) {
    if (list1.size() != list2.size()) {
      return false;
    }

    for (int i = 0; i < list1.size(); i++) {
      if (!list1.get(i).equals(list2.get(i))) {
        return false;
      }
    }

    return true;
  }

  // @include
  public static List<Integer> nextPermutation(List<Integer> perm) {
    // Find the first entry from the right that is smaller than the entry
    // immediately after it.
	  
	  int size = perm.size();
	  boolean inner=false;
	  for(int i=size-1;i>0;i--){
		  if(perm.get(i)>perm.get(i-1)){
			  for(int k=size-1;k>=i;k--){
				  if(perm.get(i-1)<perm.get(k)){
					  //swap
					  Collections.swap(perm, i-1, k);
					 
					  //sort the rest of the elements
					  // we just need to reverse the rest of the elements. no need to sort
					  Collections.reverse(perm.subList(i, size));
					  inner = true;
						break;  
				  }
			  }
		  }
		  if(inner)
			  break;
		  	
	  }
	  if(!inner){
		  
		  Collections.reverse(perm);  
	  }
	  return perm;
  
  }
//@include
 public static List<Integer> nextPermutationBook(List<Integer> perm) {
   // Find the first entry from the right that is smaller than the entry
   // immediately after it.
   int inversion_point = perm.size() - 2;
   while (inversion_point >= 0
          && perm.get(inversion_point) >= perm.get(inversion_point + 1)) {
     --inversion_point;
   }
   if (inversion_point == -1) {
     return Collections.emptyList(); // perm is the last permutation.
   }

   // Swap the smallest entry after index inversion_point that is greater than
   // perm[inversion_point]. Since entries in perm are decreasing after
   // inversion_point, if we search in reverse order, the first entry that is
   // greater than perm[inversion_point] is the entry to swap with.
   for (int i = perm.size() - 1; i > inversion_point; --i) {
     if (perm.get(i) > perm.get(inversion_point)) {
       Collections.swap(perm, inversion_point, i);
       break;
     }
   }

   // Entries in perm must appear in decreasing order after inversion_point, so
   // we simply reverse these entries to get the smallest dictionary order.
   Collections.reverse(perm.subList(inversion_point + 1, perm.size()));
   return perm;
 }
  // @exclude

  // derived from http://codeforces.com/blog/entry/3980
  private static List<Integer> goldenNextPermutation(final List<Integer> c) {
    // 1. finds the largest k, that c[k] < c[k+1]
    List<Integer> result = new ArrayList<>(c);
    int first = getFirst(result);
    if (first == -1) { // no greater permutation
      return Collections.emptyList();
    }

    // 2. find last index toSwap, that c[k] < c[toSwap]
    int toSwap = c.size() - 1;
    while (Integer.compare(c.get(first), c.get(toSwap)) >= 0) {
      --toSwap;
    }

    // 3. swap elements with indexes first and last
    Collections.swap(result, first++, toSwap);

    // 4. reverse sequence from k+1 to n (inclusive)
    toSwap = c.size() - 1;
    while (first < toSwap) {
      Collections.swap(result, first++, toSwap--);
    }

    return result;
  }

  // finds the largest k, that c[k] < c[k+1]
  // if no such k exists (there is not greater permutation), return -1
  private static int getFirst(final List<Integer> c) {
    for (int i = c.size() - 2; i >= 0; --i) {
      if (Integer.compare(c.get(i), c.get(i + 1)) < 0) {
        return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
	  
	  List<Integer> a = nextPermutation(Arrays.asList(4,2,3,1,5));
	  System.out.println("Final output \n");
	  for(int t:a)
		  System.out.println(t);
   
  }
}