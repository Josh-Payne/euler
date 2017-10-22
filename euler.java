/*
 * Josh Payne
 *
 * For anyone reading this, these are a couple problems from the Euler Project I did over Winter Break
 *
 * Sorry for the mess
 */
 
import java.io.BufferedReader;
 
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
import acm.program.*;
import acm.util.ErrorException;
import acm.util.RandomGenerator;
 
public class FindRange extends ConsoleProgram {
    public void run() {
        //multiplesOf3and5();
        //evenFibonacci();
        //largestPrime();
        //palindrome();
        //smallestDivisibleFrom1to20();
        //differenceOfSquares();
        //the10001stPrime();
        //largestAdjacentProduct();
        //pythagoreanMultiples();
        //sumOfPrimesBelow2Mil();
        //numberCrosswordProduct();
        //triangleDivisors();
        //sumOfHundred();
        //collatzSeq();
        //lattices();
        //powerDigitSum();
        //numbersAsWords();
        //triangle18();
        //countingSundays();
        //hundredFactorial();
        //amicableNums();
        modTriangle();
        //overTheRainbow();
    }
   
    private void modTriangle() {
        for (int i=0; i<=25; i++) {
            for (int k=0; k<=i; k++) {
                println(nChooseK(i, k)%2);
            }
        }
    }
   
    int nChooseK(int n, int k) {
        if (n==0) { //base case
            return 1;
        }
        else {
            if (k==0) { //left edge case
                return nChooseK(n-1, k);
            }
            else if (k==n) { //right edge case
                return nChooseK(n-1, k-1);
            }
            else {
                return nChooseK(n-1, k-1) + nChooseK(n-1, k);
            }
        }
    }
 
 
    private void anagramicWords() {
        String words = "";
        try {
            BufferedReader rd = new BufferedReader(new FileReader("p098_words.txt"));
            String wordEntry = "";
           
            while (true) {
                wordEntry = rd.readLine();
                if (wordEntry != null) {
                    words+=wordEntry;
                }
                else break;
            }
            rd.close();
        }
        catch (IOException e) {
            throw new ErrorException(e);
        }
    }
 
    private void amicableNums() {
        int sum = 0;
        int total = 0;
        int[] set = new int[10001];
        for (int i = 1; i <= 10000; i++) {
            sum = 0;
            for (int j = 1; j <= (i)/2; j++) {
               
                if (i%j == 0) {
                    sum+=j;
                }
            }
            set[i] = sum;
        }
        for (int k = 1; k <= 10000; k++) {
            System.out.println(k);
            if (set[k]<=10000) {
                if (set[set[k]] == k&&k!=set[k]) {
                    total += k;
                }
            }
        }
        println(total);
    }
 
   
   
    private void hundredFactorial() {
        String result = "1";
        int total = 0;
        for (int i = 1; i <= 99; i++) {
            result = multiply2(result, i);
        }
        for (int j = 0; j < result.length()-1; j++) {
            total+=Integer.parseInt(""+result.charAt(j));
        }
 
        System.out.println(total);
    }
 
    private String multiply2(String num, int i) {
        String newNum = "";
        String newNum1 = "";
        String newNum2 = "";
        int add = 0;
        if (i%10==0) {
            num = num + "0";
            i = Integer.parseInt(""+Integer.toString(i).charAt(0));
        }
        for (int j = num.length()-1; j >=0; j--) {
            int m = Integer.parseInt(""+num.charAt(j)) * Integer.parseInt(""+Integer.toString(i).charAt(Integer.toString(i).length()-1)) + add;
            add = 0;
            newNum1 = Integer.toString(m).charAt(Integer.toString(m).length()-1) + newNum1;
            if (m >= 10) {
                add = Integer.parseInt(Integer.toString(m).substring(0, Integer.toString(m).length()-1));
                if (j==0) {
                    newNum1 = Integer.toString(m).charAt(0) + newNum1;
                }
            }
            newNum = newNum1;
        }
        add = 0;
        if (i>10&&i%10!=0) {
            newNum = "";
            for (int j = num.length()-1; j >=0; j--) {
                int m = Integer.parseInt(""+num.charAt(j)) * Integer.parseInt(""+Integer.toString(i).charAt(0)) + add;
                add = 0;
                newNum2 = Integer.toString(m).charAt(Integer.toString(m).length()-1) + newNum2;
                if (m >= 10) {
                    add = Integer.parseInt(Integer.toString(m).substring(0, Integer.toString(m).length()-1));
                    if (j==0) {
                        newNum2 = m + newNum2.substring(1);
                    }
                }
            }
            newNum2 = newNum2 + "0";
            int a = 0;
            int b = 0;
            int add1 = 0;
            for (int k = 1; k <= newNum1.length(); k++) {
                a = Integer.parseInt(""+newNum1.charAt(newNum1.length() - k));
                b = Integer.parseInt(""+newNum2.charAt(newNum2.length() - k));
                if (k != newNum1.length()||newNum1.length()!=newNum2.length() ) {
                    newNum = Integer.toString(a+b+add1).charAt(Integer.toString(a+b+add1).length()-1) + newNum;
                }
                else {
                    newNum = Integer.toString(a+b+add1) + newNum;
                }
                int check = add1;
                add1 = 0;
                if (a+b+check >=10) {
                    add1=1;
                }
            }
            if (newNum2.length()>newNum1.length()) {
                newNum = Integer.parseInt((newNum2.substring(0, (newNum2.length()-newNum.length()) )))+add1 + newNum;
            }
 
        }
        return (newNum);
    }
 
    private String multiply(String num1, String num2){
        int product, carry=0, sum=0;
        String result = new String("");
        String partial = new String("");
        ArrayList<String> partialList = new ArrayList<String>();
 
        for(int j=num2.length()-1 ; j>=0 ; j--) {
            for(int i=num1.length()-1 ; i>=0 ; i--) {      
 
                product = Integer.parseInt((new Character(num1.charAt(i))).toString()) *
                        Integer.parseInt((new Character(num2.charAt(j))).toString()) + carry;              
                carry = product/10;
                partial = Integer.toString(product%10) + partial;              
            }      
 
            if(carry != 0)
                partial = Integer.toString(carry) + partial;
 
            partialList.add(partial);
            partial = "";
            carry = 0;
        }                          
 
        for(int i=0 ; i<partialList.size() ; i++)
            partialList.set(i, partialList.get(i) + (Long.toString( (long)java.lang.Math.pow(10.0,(double)i))).substring(1)   );        
 
        int largestPartial = partialList.get(partialList.size()-1).length();
 
        int zeroes;
        for(int i=0 ; i<partialList.size() ; i++) {
            zeroes =  largestPartial - partialList.get(i).length();
 
            if(zeroes >= 1)
                partialList.set(i, (Long.toString( (long)java.lang.Math.pow(10.0,(double)zeroes))).substring(1) + partialList.get(i)   );
        }
 
        carry = 0;
        for(int i=largestPartial-1 ; i>=0 ; i--) {
 
            sum = 0;
            for(int j=0 ; j<partialList.size() ; j++)
                sum = sum + Integer.parseInt(new Character(partialList.get(j).charAt(i)).toString());
 
            sum = sum + carry;
            carry = sum/10;        
            result = Integer.toString(sum%10) + result;    
        }
 
        if(carry != 0)
            result = Integer.toString(carry) + result;
 
        return result;
    }
 
    private void countingSundays() {
        int sundays = 0;
        int dayCycle = 0;
        int totalDays = 2;
        for(int year = 1; year <= 100; year++) {
            for (int month = 1; month <= 12; month++) {
                if (month == 1||month == 3||month == 5||month == 7||month == 8||month == 10||month == 12) {
                    for (int day = 1; day <= 31; day++) {
                        dayCycle = totalDays%7;
                        if (day==1&&dayCycle==0) {
                            sundays++;
                        }
                        totalDays++;   
                    }
                }
                if (month == 4||month == 6||month == 9||month == 11) {
                    for (int day = 1; day <= 30; day++) {
                        dayCycle = totalDays%7;
                        if (day==1&&dayCycle==0) {
                            sundays++;
                        }
                        totalDays++;   
                    }
                }
                if (month == 2) {
                    if (year%4==0&&year!=100){
                        for (int day = 1; day <= 29; day++) {
                            dayCycle = totalDays%7;
                            if (day==1&&dayCycle==0) {
                                sundays++;
                            }
                            totalDays++;   
                        }
                    }
                    else {
                        for (int day = 1; day <= 28; day++) {
                            dayCycle = totalDays%7;
                            if (day==1&&dayCycle==0) {
                                sundays++;
                            }
                            totalDays++;   
                        }
                    }
                }
            }
        }
        println(sundays);
    }
 
 
    private void triangle18() {
        String nums = "";
        String triangle1 = "75"
                + "95 64"
                + "17 47 82"
                + "18 35 87 10"
                + "20 04 82 47 65"
                + "19 01 23 75 03 34"
                + "88 02 77 73 07 63 67"
                + "99 65 04 28 06 16 70 92"
                + "41 41 26 56 83 40 80 70 33"
                + "41 48 72 33 47 32 37 16 94 29"
                + "53 71 44 65 25 43 91 52 97 51 14"
                + "70 11 33 28 77 73 17 78 39 68 17 57"
                + "91 71 52 38 17 14 91 43 58 50 27 29 48"
                + "63 66 04 68 89 53 67 30 73 16 69 87 40 31"
                + "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";
        StringTokenizer st = new StringTokenizer(triangle1);
        while (st.hasMoreTokens()) {
            nums += st.nextToken();
        }
        String[] tri = new String[15];
        int a = 0;
        for (int i = 0; i <= 14; i++) {
            System.out.println(nums.substring(2*a, 2*(a)+2*(i+1)));
            tri[i] = nums.substring(2*a, 2*(a)+ 2*(i+1));
            a+= (i+1);
            System.out.println(a);
        }
        println(tri[3]);
        int a1 = 0;
        int a2 = 0;
        int a3 = 0;
        int a4 = 0;
        int a5 = 0;
        int a6 = 0;
        int a7 = 0;
        int a8 = 0;
        int a9 = 0;
        int a10 = 0;
        int a11 = 0;
        int a12 = 0;
        int a13 = 0;
        int a14 = 0;
        int a15 = 0;
        int one = 0;
        int two = 0;
        int sum = 0;
        int compare = 0;
 
        for (one = 0; one < 1; one++) {
            if (one==two||one==two+1) {
                a1 = Integer.parseInt(tri[0].substring((one*2), ((one+1)*2)));
                System.out.println(a1);
 
 
                for (two = 0; two < 2; two++) {
                    if (two==one||two==one+1) {
                        a2 = Integer.parseInt(tri[1].substring((two*2), ((two+1)*2)));
                        System.out.println(a2);
 
 
                        for (int one1 = 0; one1 < 3; one1++) {
                            if (one1==two||one1==two+1) {
                                a3 = Integer.parseInt(tri[2].substring((one1*2), ((one1+1)*2)));
                                System.out.println(a3);
 
 
                                for (int two1 = 0; two1 < 4; two1++) {
                                    if (two1==one1||two1==one1+1) {
                                        a4 = Integer.parseInt(tri[3].substring((two1*2), ((two1+1)*2)));
                                        System.out.println(a4);
 
                                        for (int one2 = 0; one2 < 5; one2++) {
                                            if (one2==two1||one2==two1+1) {
                                                a5 = Integer.parseInt(tri[4].substring((one2*2), ((one2+1)*2)));
                                                System.out.println(a5);
 
 
                                                for (int two2 = 0; two2 < 6; two2++) {
                                                    if (two2==one2||two2==one2+1) {
                                                        a6 = Integer.parseInt(tri[5].substring((two2*2), ((two2+1)*2)));
                                                        System.out.println(a6);
 
                                                        for (int one3 = 0; one3 < 7; one3++) {
                                                            if (one3==two2||one3==two2+1) {
                                                                a7 = Integer.parseInt(tri[6].substring((one3*2), ((one3+1)*2)));
                                                                System.out.println(a7);
 
 
                                                                for (int two3 = 0; two3 < 8; two3++) {
                                                                    if (two3==one3||two3==one3+1) {
                                                                        a8 = Integer.parseInt(tri[7].substring((two3*2), ((two3+1)*2)));
                                                                        System.out.println(a8);
 
                                                                        for (int one4 = 0; one4 < 9; one4++) {
                                                                            if (one4==two3||one4==two3+1) {
                                                                                a9 = Integer.parseInt(tri[8].substring((one4*2), ((one4+1)*2)));
                                                                                System.out.println(a9);
 
 
                                                                                for (int two4 = 0; two4 < 10; two4++) {
                                                                                    if (two4==one4||two4==one4+1) {
                                                                                        a10 = Integer.parseInt(tri[9].substring((two4*2), ((two4+1)*2)));
                                                                                        System.out.println(a10);
 
                                                                                        for (int one5 = 0; one5 < 11; one5++) {
                                                                                            if (one5==two4||one5==two4+1) {
                                                                                                a11 = Integer.parseInt(tri[10].substring((one5*2), ((one5+1)*2)));
                                                                                                System.out.println(a11);
 
                                                                                                for (int two5 = 0; two5 < 12; two5++) {
                                                                                                    if (two5==one5||two5==one5+1) {
                                                                                                        a12 = Integer.parseInt(tri[11].substring((two5*2), ((two5+1)*2)));
                                                                                                        System.out.println(a12);
 
                                                                                                        for (int one6 = 0; one6 < 13; one6++) {
                                                                                                            if (one6==two5||one6==two5+1) {
                                                                                                                a13 = Integer.parseInt(tri[12].substring((one6*2), ((one6+1)*2)));
                                                                                                                System.out.println(a13);
 
 
                                                                                                                for (int two6 = 0; two6 < 14; two6++) {
                                                                                                                    if (two6==one6||two6==one6+1) {
                                                                                                                        a14 = Integer.parseInt(tri[13].substring((two6*2), ((two6+1)*2)));
                                                                                                                        System.out.println(a14);
 
                                                                                                                        for (int one7 = 0; one7 < 13; one7++) {
                                                                                                                            if (one7==two6||one7==two6+1) {
                                                                                                                                a15 = Integer.parseInt(tri[14].substring((one7*2), ((one7+1)*2)));
                                                                                                                                System.out.println(a15);
                                                                                                                                sum = a1+a2+a3+a4+a5+a6+a7+a8+a9+a10+a11+a12+a13+a14+a15;
                                                                                                                                if (sum>compare) {
                                                                                                                                    compare = sum;
                                                                                                                                }
 
 
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(compare);
    }
 
    private void numbersAsWords() {
        String singles = "onetwothreefourfivesixseveneightnine";
        String teens = "teneleventwelvethirteenfourteenfifteensixteenseventeeneighteennineteen";
        String tens = "twentythirtyfortyfiftysixtyseventyeightyninety";
        String hundred = "hundred";
        String and = "and";
        String thousand = "onethousand";
        int a = singles.length()*190;
        int b = teens.length()*10;
        int c = tens.length()*100;
        int d = hundred.length()*900;
        int e = and.length()*891;
        int f = thousand.length();
        System.out.println(and.length());
        System.out.println(a+b+c+d+e+f);
    }
 
    private void powerDigitSum() {
        String num = "2";
        for (int i = 1; i < 1000; i++) {
            String newNum = "";
            int add1 = 0;
            for (int j = num.length()-1; j >=0; j--) {
                int m = Integer.parseInt(""+num.charAt(j)) *2;
                newNum = Integer.toString((m%10)+add1) + newNum;
                if (j == 0 && m>=10) {
                    newNum = "1"+ newNum;
                }
                add1 = 0;
                if (m>=10) {
                    add1++;
                }
            }
 
            num = newNum;
        }
        println(num);
        int total = 0;
        for (int k = num.length()-1; k >=0; k--) {
            total += Integer.parseInt(""+num.charAt(k));
        }
        System.out.println(total);
        System.out.println(num);
    }
 
    private void lattices() {
        //solved using Pascal's triangle.
    }
 
    private void collatzSeq() {
        double comp = 0;
        double x = 0;
        for (double currentInt = 1; currentInt < 1000000; currentInt++) {
            if (printSequence(currentInt) > comp) {
                comp = printSequence(currentInt);
                x = currentInt;
                System.out.println(comp);
                System.out.println(x);
            }
 
        }
        System.out.println(comp);
        println(comp);
        System.out.println(x);
        println(x);
    }
 
    private int printSequence(double currentInt) {
        int operationCount = 0;
 
 
        while (currentInt != 1) {
            double result = 0;
            if (currentInt % 2 == 0) {
                result = currentInt / 2;
 
            }
            else {
                result = 3 * currentInt + 1;
 
            }
            currentInt = result;
            operationCount++;
        }
 
 
        return operationCount;
    }
 
    private void sumOfHundred() {
        String nums = "000000000000000037107287533902102798797998220837590246510135740250"
                + "000000000000000046376937677490009712648124896970078050417018260538"
                + "000000000000000074324986199524741059474233309513058123726617309629"
                + "000000000000000091942213363574161572522430563301811072406154908250"
                + "000000000000000023067588207539346171171980310421047513778063246676"
                + "000000000000000089261670696623633820136378418383684178734361726757"
                + "000000000000000028112879812849979408065481931592621691275889832738"
                + "000000000000000044274228917432520321923589422876796487670272189318"
                + "000000000000000047451445736001306439091167216856844588711603153276"
                + "000000000000000070386486105843025439939619828917593665686757934951"
                + "000000000000000062176457141856560629502157223196586755079324193331"
                + "000000000000000064906352462741904929101432445813822663347944758178"
                + "000000000000000092575867718337217661963751590579239728245598838407"
                + "000000000000000058203565325359399008402633568948830189458628227828"
                + "000000000000000080181199384826282014278194139940567587151170094390"
                + "000000000000000035398664372827112653829987240784473053190104293586"
                + "000000000000000086515506006295864861532075273371959191420517255829"
                + "000000000000000071693888707715466499115593487603532921714970056938"
                + "000000000000000054370070576826684624621495650076471787294438377604"
                + "000000000000000053282654108756828443191190634694037855217779295145"
                + "000000000000000036123272525000296071075082563815656710885258350721"
                + "000000000000000045876576172410976447339110607218265236877223636045"
                + "000000000000000017423706905851860660448207621209813287860733969412"
                + "000000000000000081142660418086830619328460811191061556940512689692"
                + "000000000000000051934325451728388641918047049293215058642563049483"
                + "000000000000000062467221648435076201727918039944693004732956340691"
                + "000000000000000015732444386908125794514089057706229429197107928209"
                + "000000000000000055037687525678773091862540744969844508330393682126"
                + "000000000000000018336384825330154686196124348767681297534375946515"
                + "000000000000000080386287592878490201521685554828717201219257766954"
                + "000000000000000078182833757993103614740356856449095527097864797581"
                + "000000000000000016726320100436897842553539920931837441497806860984"
                + "000000000000000048403098129077791799088218795327364475675590848030"
                + "000000000000000087086987551392711854517078544161852424320693150332"
                + "000000000000000059959406895756536782107074926966537676326235447210"
                + "000000000000000069793950679652694742597709739166693763042633987085"
                + "000000000000000041052684708299085211399427365734116182760315001271"
                + "000000000000000065378607361501080857009149939512557028198746004375"
                + "000000000000000035829035317434717326932123578154982629742552737307"
                + "000000000000000094953759765105305946966067683156574377167401875275"
                + "000000000000000088902802571733229619176668713819931811048770190271"
                + "000000000000000025267680276078003013678680992525463401061632866526"
                + "000000000000000036270218540497705585629946580636237993140746255962"
                + "000000000000000024074486908231174977792365466257246923322810917141"
                + "000000000000000091430288197103288597806669760892938638285025333403"
                + "000000000000000034413065578016127815921815005561868836468420090470"
                + "000000000000000023053081172816430487623791969842487255036638784583"
                + "000000000000000011487696932154902810424020138335124462181441773470"
                + "000000000000000063783299490636259666498587618221225225512486764533"
                + "000000000000000067720186971698544312419572409913959008952310058822"
                + "000000000000000095548255300263520781532296796249481641953868218774"
                + "000000000000000076085327132285723110424803456124867697064507995236"
                + "000000000000000037774242535411291684276865538926205024910326572967"
                + "000000000000000023701913275725675285653248258265463092207058596522"
                + "000000000000000029798860272258331913126375147341994889534765745501"
                + "000000000000000018495701454879288984856827726077713721403798879715"
                + "000000000000000038298203783031473527721580348144513491373226651381"
                + "000000000000000034829543829199918180278916522431027392251122869539"
                + "000000000000000040957953066405232632538044100059654939159879593635"
                + "000000000000000029746152185502371307642255121183693803580388584903"
                + "000000000000000041698116222072977186158236678424689157993532961922"
                + "000000000000000062467957194401269043877107275048102390895523597457"
                + "000000000000000023189706772547915061505504953922979530901129967519"
                + "000000000000000086188088225875314529584099251203829009407770775672"
                + "000000000000000011306739708304724483816533873502340845647058077308"
                + "000000000000000082959174767140363198008187129011875491310547126581"
                + "000000000000000097623331044818386269515456334926366572897563400500"
                + "000000000000000042846280183517070527831839425882145521227251250327"
                + "000000000000000055121603546981200581762165212827652751691296897789"
                + "000000000000000032238195734329339946437501907836945765883352399886"
                + "000000000000000075506164965184775180738168837861091527357929701337"
                + "000000000000000062177842752192623401942399639168044983993173312731"
                + "000000000000000032924185707147349566916674687634660915035914677504"
                + "000000000000000099518671430235219628894890102423325116913619626622"
                + "000000000000000073267460800591547471830798392868535206946944540724"
                + "000000000000000076841822524674417161514036427982273348055556214818"
                + "000000000000000097142617910342598647204516893989422179826088076852"
                + "000000000000000087783646182799346313767754307809363333018982642090"
                + "000000000000000010848802521674670883215120185883543223812876952786"
                + "000000000000000071329612474782464538636993009049310363619763878039"
                + "000000000000000062184073572399794223406235393808339651327408011116"
                + "000000000000000066627891981488087797941876876144230030984490851411"
                + "000000000000000060661826293682836764744779239180335110989069790714"
                + "000000000000000085786944089552990653640447425576083659976645795096"
                + "000000000000000066024396409905389607120198219976047599490197230297"
                + "000000000000000064913982680032973156037120041377903785566085089252"
                + "000000000000000016730939319872750275468906903707539413042652315011"
                + "000000000000000094809377245048795150954100921645863754710598436791"
                + "000000000000000078639167021187492431995700641917969777599028300699"
                + "000000000000000015368713711936614952811305876380278410754449733078"
                + "000000000000000040789923115535562561142322423255033685442488917353"
                + "000000000000000044889911501440648020369068063960672322193204149535"
                + "000000000000000041503128880339536053299340368006977710650566631954"
                + "000000000000000081234880673210146739058568557934581403627822703280"
                + "000000000000000082616570773948327592232845941706525094512325230608"
                + "000000000000000022918802058777319719839450180888072429661980811197"
                + "000000000000000077158542502016545090413245809786882778948721859617"
                + "000000000000000072107838435069186155435662884062257473692284509516"
                + "000000000000000020849603980134001723930671666823555245252804609722"
                + "000000000000000053503534226472524250874054075591789781264330331690";
 
        //println(findSumOfLast(nums));
        println(findSumOfHundred(nums));
    }
 
    private String findSumOfHundred(String nums) {
        String size = "000000000000000053503534226472524250874054075591789781264330331690";
        int a = 0;
        int b = 0;
        String sum = "";
        for (int i = size.length()-1; i >= 0; i--) {
            a = 0;
            for (int j = 0; j < 100; j++) {
 
                a += Integer.parseInt(""+nums.charAt(i+(j*size.length())));
            }
            a+=b;
            if (Integer.toString(a).length()==3) {
                b = Integer.parseInt(""+Integer.toString(a).substring(0, 2));
            }
            else if (Integer.toString(a).length()==2) {
                b = Integer.parseInt(""+Integer.toString(a).charAt(0));
            }
            else b = 0;
            println(a);
            println(b);
            sum = Integer.toString(a).charAt(Integer.toString(a).length()-1) + sum;
            System.out.println(sum);
        }
        System.out.println(sum);
        return sum;
    }
 
    private void triangleDivisors() {
        println(findTriangleDivisors());
    }
 
    private int findTriangleDivisors() {
        int triNum = 0;
        int i = 1;
        while(checkDivisors(triNum) < 500) {
            triNum += i;
            i++;
        }
        System.out.println(triNum);
        return triNum;
 
    }
 
    private int checkDivisors(int triNum) {
        int n = 0;
        for (int i = 1; i < triNum; i++) {
            if (triNum%i == 0) {
                n++;
            }
        }
        return n;
    }
 
    private void numberCrosswordProduct() {
        String nums = "";
        String numTable = "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 "
                + "77 91 08 49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 "
                + "04 56 62 00 81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 "
                + "03 49 13 36 65 52 70 95 23 04 60 11 42 69 24 68 56 01 32 "
                + "56 71 37 02 36 91 22 31 16 71 51 67 63 89 41 92 36 54 22 "
                + "40 40 28 66 33 13 80 24 47 32 60 99 03 45 02 44 75 33 53 "
                + "78 36 84 20 35 17 12 50 32 98 81 28 64 23 67 10 26 38 40 "
                + "67 59 54 70 66 18 38 64 70 67 26 20 68 02 62 12 20 95 63 "
                + "94 39 63 08 40 91 66 49 94 21 24 55 58 05 66 73 99 26 97 "
                + "17 78 78 96 83 14 88 34 89 63 72 21 36 23 09 75 00 76 44 "
                + "20 45 35 14 00 61 33 97 34 31 33 95 78 17 53 28 22 75 31 "
                + "67 15 94 03 80 04 62 16 14 09 53 56 92 16 39 05 42 96 35 "
                + "31 47 55 58 88 24 00 17 54 24 36 29 85 57 86 56 00 48 35 "
                + "71 89 07 05 44 44 37 44 60 21 58 51 54 17 58 19 80 81 68 "
                + "05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40 04 52 08 "
                + "83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66 88 36 "
                + "68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69 04 "
                + "42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36 "
                + "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 "
                + "16 20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 "
                + "05 54 01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48";
        StringTokenizer st = new StringTokenizer(numTable);
        while (st.hasMoreTokens()) {
            nums += st.nextToken();
        }
        println(findNumberCrosswordProduct(nums));
    }
 
    private int findNumberCrosswordProduct(String nums) {
        int x = 0;
        if (down(nums) > x) {
            x = down(nums);
        }
        if (sideWays(nums) > x) {
            x = sideWays(nums);
        }
        if (diagRight(nums) > x) {
            x = diagRight(nums);
        }
        if (diagLeft(nums) > x) {
            x = diagLeft(nums);
        }
        return x;
    }
 
    private int diagLeft(String nums) {
        int x = 0;
        for (int i = 0; i < 680; i+=2) {
            int start = i;
            int end = i+2;
            if (i%40 >= 8) {
                int a = Integer.parseInt(nums.substring(start, end));
                int b = Integer.parseInt(nums.substring(start+38, end+38));
                int c = Integer.parseInt(nums.substring(start+76, end+76));
                int d = Integer.parseInt(nums.substring(start+114, end+114));
                int e = a*b*c*d;
                if (e>x) {
                    x=e;
                }
            }
        }
        return x;
    }
 
    private int diagRight(String nums) {
        int x = 0;
        for (int i = 0; i < 674; i+=2) {
            int start = i;
            int end = i+2;
            if (i%40 <= 32) {
                int a = Integer.parseInt(nums.substring(start, end));
                int b = Integer.parseInt(nums.substring(start+42, end+42));
                int c = Integer.parseInt(nums.substring(start+84, end+84));
                int d = Integer.parseInt(nums.substring(start+126, end+126));
                int e = a*b*c*d;
                if (e>x) {
                    x=e;
                }
            }
        }
        return x;
    }
 
    private int sideWays(String nums) {
        int x = 0;
        for (int i = 0; i < 794; i+=2) {
            int start = i;
            int end = i+2;
            if (i%40 <= 32) {
                int a = Integer.parseInt(nums.substring(start, end));
                int b = Integer.parseInt(nums.substring(start+2, end+2));
                int c = Integer.parseInt(nums.substring(start+4, end+4));
                int d = Integer.parseInt(nums.substring(start+6, end+6));
                int e = a*b*c*d;
                if (e>x) {
                    x=e;
                }
            }
        }
        return x;
    }
 
    private int down(String nums) {
        int x = 0;
        for (int i = 0; i < 680; i+=2) {
            int start = i;
            int end = i+2;
            int a = Integer.parseInt(nums.substring(start, end));
            int b = Integer.parseInt(nums.substring(start+40, end+40));
            int c = Integer.parseInt(nums.substring(start+80, end+80));
            int d = Integer.parseInt(nums.substring(start+120, end+120));
            int e = a*b*c*d;
            if (e>x) {
                x=e;
            }
        }
        return x;
    }
 
    private void sumOfPrimesBelow2Mil() {
        println(findPrimeMults());
    }
 
    private long findPrimeMults() {
        long x = 0;
        for (int i = 2; i <= 2000000; i++) {
            if (isPrime(i)) {
                x += i;
            }
        }
 
        return x;
    }
 
    private void pythagoreanMultiples() {
        int a, b, c;
        for (a = 1; a < 500; a++)  {
            for (b = a; b < 500; b++)  {
                for (c = b; c < 500; c++)  {
                    if ((a * a + b * b ==  c * c) && (a + b + c == 1000)) {
                        println(a * b * c);
                    }
                }
            }
        }
    }
 
    private void largestAdjacentProduct() {
        String numString = "73167176531330624919225119674426574742355349194934"
                + "96983520312774506326239578318016984801869478851843858615607"
                + "89112949495459501737958331952853208805511125406987471585238"
                + "63050715693290963295227443043557668966489504452445231617318"
                + "56403098711121722383113622298934233803081353362766142828064"
                + "44486645238749303589072962904915604407723907138105158593079"
                + "60866701724271218839987979087922749219016997208880937766572"
                + "73330010533678812202354218097512545405947522435258490771167"
                + "05560136048395864467063244157221553975369781797784617406495"
                + "51492908625693219784686224828397224137565705605749026140797"
                + "29686524145351004748216637048440319989000889524345065854122"
                + "75886668811642717147992444292823086346567481391912316282458"
                + "61786645835912456652947654568284891288314260769004224219022"
                + "67105562632111110937054421750694165896040807198403850962455"
                + "44436298123098787992724428490918884580156166097919133875499"
                + "20052406368991256071760605886116467109405077541002256983155"
                + "20005593572972571636269561882670428252483600823257530420752963450";
 
        println(findlargestAdjacentProduct(numString));
    }
 
    private double findlargestAdjacentProduct(String numString) {
        double x = 0;
        double max = 0;
        for (int i = 0; i < 987; i++) {
            int a = Integer.parseInt(""+numString.charAt(i));
            int b = Integer.parseInt(""+numString.charAt(i+1));
            int c = Integer.parseInt(""+numString.charAt(i+2));
            int d = Integer.parseInt(""+numString.charAt(i+3));
            int e = Integer.parseInt(""+numString.charAt(i+4));
            int f = Integer.parseInt(""+numString.charAt(i+5));
            int g = Integer.parseInt(""+numString.charAt(i+6));
            int h = Integer.parseInt(""+numString.charAt(i+7));
            int j = Integer.parseInt(""+numString.charAt(i+8));
            int k = Integer.parseInt(""+numString.charAt(i+9));
            int l = Integer.parseInt(""+numString.charAt(i+10));
            int m = Integer.parseInt(""+numString.charAt(i+11));
            int n = Integer.parseInt(""+numString.charAt(i+12));
 
            int one = a*b*c*d/100;
            int two = e*f*g*h/100;
            int three = j*k*l*m*n/100;
            x = one*two*three;
 
            if (x > max) {
                System.out.println(i);
                max = x;
            }
        }
 
        return max;
    }
 
    private void the10001stPrime() {
        println(find10001stPrime());
    }
 
    private int find10001stPrime() {
        int counter = 0;
        int i = 1;
 
        for (i = 2; i <= 50000000; i++) {                
            if (isPrime(i)) {
                counter++;
            }
            if (counter == 10001) {
                break;
            }
        }
        return i;
    }
 
 
    private boolean isPrime(int n) {
        for(int i=2;i<n;i++) {
            if(n%i==0)
                return false;
        }
        return true;
    }
 
 
    private void differenceOfSquares() {
        println(sumProductDifferenceOfSquares());
    }
 
    private int sumProductDifferenceOfSquares() {
        int sum = 0;
        int product = 0;
        int difference;
        for (int i = 1; i <= 100; i++) {
            sum +=i;
            product +=i*i;
        }
        sum*=sum;
        difference = sum - product;
        return difference;
    }
 
    private void smallestDivisibleFrom1to20() {
        println(findSmallestDivisible());
    }
 
    private int findSmallestDivisible() {
        int x = 11;
        while (true) {
            if (x%11 != 0) {
                x++;
            }
            else if (x%12 != 0) {
                x++;
            }
            else if (x%13 != 0) {
                x++;
            }
            else if (x%14 != 0) {
                x++;
            }
            else if (x%15 != 0) {
                x++;
            }
            else if (x%16 != 0) {
                x++;
            }
            else if (x%17 != 0) {
                x++;
            }
            else if (x%18 != 0) {
                x++;
            }
            else if (x%19 != 0) {
                x++;
            }
            else if (x%20 != 0) {
                x++;
            }
            else break;
        }
        return x;
    }
 
    private void palindrome() {
        println(palindromeProduct());
    }
 
    private int palindromeProduct() {
        int x = 0;
        for (int i = 100; i <= 999; i++) {
            for (int j = 100; j <= 999; j++) {
                if (i < j) {
                    int k = i*j;
                    String palindrome = Integer.toString(k);
                    int a = Integer.parseInt(""+palindrome.charAt(0));
                    int b = Integer.parseInt(""+palindrome.charAt(palindrome.length()-1));
                    int c = Integer.parseInt(""+palindrome.charAt(1));
                    int d = Integer.parseInt(""+palindrome.charAt(palindrome.length()-2));
                    int e = Integer.parseInt(""+palindrome.charAt(2));
                    int f = Integer.parseInt(""+palindrome.charAt(palindrome.length()-3));
                    if (a == b && c == d && e == f && k>x) {
                        x = k;
                    }
                }
            }
        }
        return x;
    }
 
    private void largestPrime() {
        println(largestPrimeFactor());
    }
 
    private double largestPrimeFactor() {
        int i = 2;
        int r = 0;
        long l1 = 600851475143L;
        while(l1 > 1) {
            if((l1 % i) == 0) {
                l1 /= i;
                if(r < i)
                    r = i;
                i = 2;
            }
            else i++;
        }
        return r;
    }
 
    private void evenFibonacci() {
        println(evenFibSum());
    }
 
    private int evenFibSum() {
        int x = 2;
        int y = 1;
        int z = 0;
        int n = 0;
        while (x < 4000000) {
            z = x;
            x = x + y;
            y = z;
            if (x%2 == 0&&x < 4000000) {
                n += x;
                println(x);
            }
        }
        return n;
    }
 
    private void multiplesOf3and5() {
        println(sum3() + sum5() - sum15());
    }
 
    private int sum3() {
        int x = 0;
        for (int i = 0; i< 1000; i+=3) {
            x += i;
        }
        return x;
    }
    private int sum5() {
        int x = 0;
        for (int i = 0; i< 1000; i+=5) {
            x += i;
        }
        return x;
    }
    private int sum15() {
        int x = 0;
        for (int i = 0; i< 1000; i+=15) {
            x += i;
        }
        return x;
    }
}