package SearchEngine;


public class EditDistance {
	
	
	public static int editDistanceCal(String str1, String str2) {
		int strLen1 = str1.length();
		int strLen2 = str2.length();
	 
		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] distance = new int[strLen1 + 1][strLen2 + 1];
	 
		for (int i = 0; i <= strLen1; i++) 
			distance[i][0] = i;
		
	 
		for (int j = 0; j <= strLen2; j++) 
			distance[0][j] = j;
		
	 
		//iterate though, and check last char
		for (int i = 0; i < strLen1; i++) 
		{
			char c1 = str1.charAt(i);
			for (int j = 0; j < strLen2; j++) 
			{
				char c2 = str2.charAt(j);
	 
				//if last two chars equal
				if (c1 == c2) 
				{
					//update dp value for +1 length
					distance[i + 1][j + 1] = distance[i][j];
				} 
				else 
				{
					int replace = distance[i][j] + 1;
					int insert = distance[i][j + 1] + 1;
					int delete = distance[i + 1][j] + 1;
					int min =0;
					if(replace>insert)
						min = insert;
					else
						min = replace;
					
					if(delete<min)
						min = delete;
					
					distance[i + 1][j + 1] = min;
				}
			}
		}
	 
		return distance[strLen1][strLen2];
	}
	
}
