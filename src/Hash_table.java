public class Hash_table {
    
    public Linked_list<wordInfo> words[];
    public Linked_list<wordInfo> word;
    public int min, pmin, max, pmax, avg, sum, iterations;
    public int Size = 10, current = 0;
    public boolean double_it;


    public Hash_table(int size) {
        words = new Linked_list[size];
        sum = 0; iterations = 0; pmin = 1;
        double_it = false;
    }

    public Hash_table() {
        words = new Linked_list[Size];
        sum = 0; iterations = 0; pmin = 1;
        double_it = true;
    }


    public int hashing(String s) {
		
		int result = 0;
		
		for(int i = 0; i < s.length(); i++) {
			
			result += s.charAt(i) << (i & 15);
		}
		return result % words.length;
	}

    public int hashing(String s, int Size) {
		
		int result = 0;
		
		for(int i = 0; i < s.length(); i++) {
			
			result += s.charAt(i) << (i & 15);
		}
		return result % Size;
	}

    public void add(String str) {
        int index = hashing(str);
        boolean found = false;

        Linked_list<wordInfo>.Node<wordInfo> temp;
        if(words[index] != null) {
            temp = words[index].head;
            max = 0;
            for(int i = 0; i < words[index].size; i++) {
                if(temp.data.word.equals(str)) {
                    temp.data.occurences++;
                    found = true;
                }
                temp = temp.next;
                max += 1;
            }

            if(!found) {
                words[index].add(new wordInfo(str, 1));
            }

            temp = words[index].head;
        } else {
            word = new Linked_list<wordInfo>();
            word.add(new wordInfo(str, 1));
            words[index] = word;
            current++;

            if(current >= Size && double_it) double_it();
        }

        calc_statistics();
    }

    public void print() {
        for(int i = 0; i < words.length; i++) {
            Linked_list<wordInfo>.Node<wordInfo> temp = words[i].head;
            while(temp != null) {
                System.out.println(temp.data.word + " ==> " + temp.data.occurences);
                temp = temp.next;
            }
        }
    }

    public void statistics() {

        int shortest = words[0].size, longest = 0;
        for(int i = 0; i < words.length; i++) {
            if(words[i] != null) {
                System.out.println("index: " + i + "\tsize: " + words[i].size);

                if(words[i].size >= longest) longest = words[i].size;
                if(words[i].size <= shortest) shortest = words[i].size;
            }
            
        }

        System.out.println("shortest = " + shortest + "\tlongest = " + longest);
        System.out.println("min = " + pmin + "\tmax = " + pmax + "\tavg = " + avg);
    }

    private void calc_statistics() {

        iterations++;
        sum += max;
        min = max;
        if(max >= pmax) pmax = max;
        if(pmin > min) pmin = min;
        avg = sum / iterations;
    }

    private void double_it() {

        if(double_it) {

            sum = 0;
            iterations = 0;

            this.Size *= 2;
            Linked_list<wordInfo> temp_words[] = new Linked_list[Size];

            for(int i = 0; i < words.length; i++) {
                Linked_list<wordInfo>.Node<wordInfo> temp = words[i].head;
                max = 0;
                while(temp != null) {
                    int index = hashing(temp.data.word, Size);
                
                    word = new Linked_list<wordInfo>();
                    word.add(new wordInfo(temp.data.word, temp.data.occurences));
                    temp_words[index] = word;

                    temp = temp.next;
                    max += 1;
                }

                
            }

            current = 0;
            for(int j = 0; j < temp_words.length; j++) {
                if(temp_words[j] != null) current++;
            }

            words = temp_words;
            
        }
        
    }

}
