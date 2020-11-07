package com.epi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * you can solve without having to recurse for the output after you build the
 * list. However, that way is time limit exceeded
 *
 */
public class WordBreak2{

    List<String> result = new ArrayList<>();
    Set<String> dict1 =  new HashSet<>();
    HashMap<Integer,Set<String>> hm = new HashMap<>();

    public List<String> findWords(String s, String[] dict) {

        if (dict == null){
            return null;
        }

        for(String t:dict){
            dict1.add(t);
        }

        return dfs(new HashMap<Integer, List<String>>(), 0, s);
        // optimizedDfs(s);
        // build("",s.length());
        // return new ArrayList<>(hm.get(s.length()));
    }
    public void optimizedDfs(String s){
        for(int i=0;i<s.length();i++){
            if(hm.containsKey(i) || i==0){
                for(int j=i+1;j<=s.length();j++){
                    if(dict1.contains(s.substring(i,j))){
                        /*
                         * HashSet<String> toAdd = new HashSet<>(); if (i != 0) { for (String string :
                         * hm.get(i)) { toAdd.add(string + " " + s.substring(i, j)); } } else {
                         * toAdd.add(s.substring(i, j)); }
                         */
                        hm.computeIfAbsent(j, key -> new HashSet<>()).add(s.substring(i, j));
                    }
                }
            }
        }

    }
    public void build(String s, int id){
        if(id==0){
            result.add(s);
            return;
        }
        if(hm.containsKey(id)){
            for(int i=hm.size();i>0;i--){

            }
            for(String t:hm.get(id)){
                build(t+" "+s,id-t.length());
            }
        }
        return;
    }

    public List<String> dfs(Map<Integer, List<String>> map, int i, String s) {
        if (i == s.length()) {
            return new ArrayList<>();
        }
        if (map.containsKey(i)) {
            return map.get(i);
        }
        List<String> res = new ArrayList<>();

        for (int k = i + 1; k <= s.length(); k++) {
            if (dict1.contains(s.substring(i, k))) {
                List<String> tmp = dfs(map, k, s);
                if (tmp.isEmpty() && k == s.length()) {
                    res.add(s.substring(i, k));
                } else {
                    for (String t : tmp) {
                        res.add(s.substring(i, k) + " " + t);
                    }
                }
            }
        }
        map.put(i, res);
        return res;
    }

    public static void main(String[] args){
        //["f a jbeokiakf m l a c b i nj d nj d m m f h a",
        // "fa jbeokiakf m l a c b i nj d nj d m m f h a",
        //"f a jbeokiakf m l a c bi nj d nj d m m f h a","fa jbeokiakf m l a c bi nj d nj d m m f h a",
        //"f a jbeokiakf mlacbi nj d nj d m m f h a","fa jbeokiakf mlacbi nj d nj d m m f h a",
        //"f a jbeokiakf m l a c b i njdnjdmmfha","fa jbeokiakf m l a c b i njdnjdmmfha","f a jbeokiakf m l a c bi njdnjdmmfha",
        //"fa jbeokiakf m l a c bi njdnjdmmfha","f a jbeokiakf mlacbi njdnjdmmfha","fa jbeokiakf mlacbi njdnjdmmfha"]
        /*String s = "fajbeokiakfmlacbinjdnjdmmfha";//"catsanddog";
		String[] dict = {"be","ellaekgjhibcomc","ahaklkan","jcm","lchidklmcone","ljmdgagaen","giioojldjkfnno","el",
				"eibjaffacjll","hn","hbjakhjneml","foi","nbhf","aigf","cfdlnc","fa","amakgofedhkghgl","ddhmhdhioh","ijoddeabbiei",
				"giamcgco","nholghlfbbendi","emlc","m","elgibme","behkignjenmh","lodkkjgioe","doe","hgflgakna","macghogdidmdm","ec",
				"kncigolkog","ljooio","lch","gkaclkbjn","ofiaglfoffl","alhfbb","cfmdbgo","cfcnajknk","jfh","almbgdjnbbbgmhb","dmlnnohf",
				"olojeejfafc","ndgcmgoe","cmkdjilfeo","bengdd","enfg","dbngiggni","anmkljn","njdnjdmmfha","ndimmddfmhe","hmkdjkhhiilnf",
				"ehd","jfdl","dlgki","bhoflnomibkki","lg","fjojjnnkdfenhol","lefhhl","nimdl","gejgomblmim","ahbnlmlfmejjj","glhacaojnf",
				"mfjdhnhdkm","do","fnh","mnmjdk","hfjgdlnnb","hniolfhkhbie","ldgodonogcab","mabjnnohnijhn","aceojlkmdg","aedfljg","cehk",
				"jag","oniegflnhje","jo","maokc","jkbndc","djbn","dajkdblojkf","dmen","kcdjdocinenc","cgindbm","h","odaof","cmogcbgj",
				"anjahlgbbmba","haoe","ggacnminj","ilcfjoedhe","klookammgofl","onnmenn","mbdneaioo","jc","dekgil","bjdfibfd","hfbnlgmlllcb",
				"afebehf","obekljbnh","eoaedhjk","nobamccd","mdieojoknf","komcglmakkaa","jcliimcc","jmmgbmha","gdogjnn","ednembco","dgno",
				"jiaheeabifahfmo","djkcgnakkh","kdkiglgf","eb","fmdnlhj","eicohdciejc","jgofmankkf","o","nnmomkmkmiaoga","njchkccln","ndamha",
				"eleanmojdi","ebkl","jcageehlelcg","acfddofjihgmn","iklaomfhjm","io","igmob","lfnhnlnigbbignk","anihfojmedf","nj","oilcabalhb",
				"adjfbkfjch","lbfb","mgfnngfccb","jhmhggm","dnllc","c","ljim","jmikd","mdfimdgac","fhbclgo","edclcdia","nelmfjejff","i",
				"cmcbbckdnjcoo","cddocce","hc","keh","keofhnhemd","biln","mjcnbjmkikon","fekbdnkolahh","hkfmjbj","mjoj","jn","ilof","ifhfk",
				"aofmg","nofljgmmmf","hcdifeiclbchlf","imlijgdg","ocdiiemcmbkglm","nhoekmlkjfoa","kibffkbleedda","kdhdjekccbkc","bcbflcag",
				"jekmkdimnnjjoo","mmgfljchalbem","kchk","oi","ncf","jembgfa","l","kfkeianmmmdacl","ecjkkfggj","jdgcfnhfjonkhig","jhagiokii",
				"nifm","bbjjlj","adajlokomibfg","ojk","lockdel","bh","hoojolglchck","conko","eadi","kfigoijnfimolen","g","dbnj","cojkbmo",
				"hh","mcdbh","ngdmgioen","ehjagfohnolkho","dgfgdlc","aoglneoh","gbc","ijjckddeicld","imekih","liiaecniil","hahejbhgiclb",
				"fnmojm","ablihjhggiahhno","colloaakco","jhobddaanbhmlg","cbfajfhkoh","cim","laghknigabn","dcbnbkegkjam","gem","ljjim",
				"icclogji","omidhe","f","giiaclfcjkagl","ndcjldekjnkekm","aiikdccohcj","mkbmb","oomhhafobic","bkacdjfgbggn","ahghdoahbi",
				"hedm","eeoj","bdgdlfollegej","eg","dfeb","dkffkid","hcne","gjkohnaaabn","jfeododjgdhlfbf","clfkmconnkfb","abnbkcni","hk",
				"ghnmhjm","oibjibmkaibdefa","hjambim","oe","aao","jil","fmhomflfen","hlidcklnmb","hiaonkhd","bibbmkandf","hke","bmfcionm",
				"inhcnlkbkkmjicn","jckjedhgoghi","chmik","mnjldknhaec","hocbccbg","ljadj","lciikgnlj","ifjjhkbhifione","foikblanoco","ode",
				"mjc","fhklofh","mmoklkkog","hocbojmhffeajo","ccmmd","bkkh","nhhgcflniebkme","lfohikenfbjacli","cmehijnihijgng","caa","bmk",
				"emofof","jjagiogohfab","ibh","eoacdlnodalkjbl","cbbjbbnjom","iljiomeloehen","gignlngclmh","b","ll","dokgngnocde",
				"cienegffibgieba","agbachloidg","mlelnafokd","nmcmka","akeogjbjcnf","nebdic","a","efc","ljdk","jhcag","bkbikbjgae","mcjlgjeo",
				"lo","dbiofobl","cehebiljff","eeagngm","ondahcjiel","coblanndhlhoggj","jaobmjml","jfejjinofek","hhnna","gdhcn","acelcomgkgohm",
				"njkkjkkln","jmc","hkekoho","boefec","cioibfgjmhb","ebggdbeimn","emhg","cfghkhii","d","k","khoddedia","nhje","eebkfml","bohhd",
				"kg","n","ilgemokdehcbaif","cldicda","einij","akmabcgfn","fmkmcn","bnlfbagkke","oakbgjejmcncj","iehdfadgoik","kkcfo",
				"jmjkmfcacjjnd","ndokhh","hjfeelhckkjjmj","dnomohejbodkb","jcmblncjadno","oiofcodobiml","ddmillkncjfdfj","aihenmkdnhdhkhf",
				"bfdbakeilfdojnc","jjhbkbne","aigabk","cae","oednojjb","gdoe","jokjceohkmbm","offkanbahigo","kfomigbfddjli","dkkjobgkcejei",
				"mdilld","bofkika","kkinig","cljcflbghjmhmke","kmbjlgdcbdjn","bkgbmoahda","kmnajjdemggnfg","mgjndldil","iemb","kehaokgjg",
				"icign","oijmaolehmoo","amhgldifmgekhe","diacnollhi","lnjhdaafadl","bdfiackhogoje","ebjlfa","deabkgfhnead","gadcob","haa",
				"dbhnbhjcmmab","bbmjainilbbej","dc","bgjgafnjjflne","ehholgnn","fmhccbnc","mdnfl","feeejdgc","mfhlobdadooh","ojna",
				"gkgjnijdbgo","ghngnhn","nhnjaiaadiedgg","nk","hmd","nmbmijaffogl","onkcgbgmago","gfli","ofjlec","nlfnbkkdc","hakilani",
				"bofjdjkhllb","ocjncleljnecfc","gdonnkodmkejhf","fiflchanfllgnf","kaejakoibgln","hmdlfioacgaci","honmfbcog","mlacbi","gf",
				"ejbbemoeha","acfjegee","lllflaocnnkeadi","mdgoebfgacecmbg","faejgln","kmlmhffgcmekm","akcjmgdg","kmhhh","fdohjehacdln","e",
				"ojba","ohadmod","eaenkdiaokl","dii","cgfjaklblafeifo","imoeflkcgbbem","nbjkmb","jjgm","hofgelg","cnihecmdigdgflg",
				"fnmikkeldjgb","onlhgonldjaedh","fmkdn","kfbcbleen","oejioibnmab","cg","meadghbocjnj","hmmdnkegfeieijn","ijgenomhndlje",
				"maccdcgfjig","iabemie","mlfg","mdblmdaechmeaml","dhlafgjo","eabbiila","kf","oehggehfmijlfmg","klljaejidfhbon","akmbgmignoag",
				"jgbkngmigdfm","kjeelnbn","ajaa","mlcjoiaahoiga","oalnielba","ffmobgkc","kmhoknfffdmo","nagjiffnjhh","dlehllomjok",
				"agaejefhdbk","nnegoijfdj","ndl","dhfginocgi","nflmglgh","bcd","gbgjijemmdio","jk","gidgjbmb","hi","lmgoah","fdebefcech",
				"ach","bahaoj","ccmmblgibgjahi","moid","jhilgedidndm","ldiakemnj","bbnibccm","jkbneoaheaajnm","clkgmbjlgdnl","lobbdldifnnijh",
				"dnmih","jglia","didicmghfe","dlhbcfclf","akbmioocoihkfh","foofdldm","imenimfcame","ifekbmgnbdkc","jjlkaabdollola","gie",
				"hbaj","noomfnfccmgaa","dcjffeg","nb","obdb","lolgjflimkib","eaiigminlakkb","cia","hkf","jknfklaio","igklbiomo","jfjgh",
				"ekgnkfnhjcch","kmonfcclieehlik","oggkmccklnmj","bedhobcl","egmnhajcnhcdgb","imfdhekamfel","bmmkhfdbm","gnjfbcjlecfn",
				"llmkgclm","gafinbnhfe","mlbfedkoeeddfao","kklcdmglleb","ckekmeiea","mi","kfejn","lm","mk","abkoajocfdili","jidac",
				"jonhkanccl","lllodjgnmm","abfeaodlmjkngol","cdncnh","lkcb","abhilnmmhijab","hiljkfakojjld","mbboobkaolkljo","jhkblobaofgoh",
				"ncm","mgbdhmcicomf","oag","akmjdd","abkenodnhj","mljf","afb","afejkobmiffeee","oollnkilabmb","gfaocokmcmjlmb","cokmecdd",
				"bo","endocdnmjiek","bcf","lhllbagiel","bhihgofhj","ffce","neio","ofbfiiab","kjdo","lgfggnamceeo","kofledoinamcj",
				"femhndomndoakoa","fmodaigcka","omakggcalhn","hhhogmcbjnhelkk","mgah","jghjjfmk","ecolelfmcb","eajjkdncafhhgab","obno",
				"fifigfeok","laafjimienff","beckbbmhmofb","nafhihmgnikd","cbcfnhlkne","kao","nlkfhbm","fmh","ohfek","oj","hifgcgi","adhkn",
				"lffgmodeafnn","ngchmhdbmhmhh","mcffimhnlffab","blhmkdhbnhbb","kkb","lgkine","hgfbdbfffanhik","joebhbh","img","kglcddmloo",
				"hoflgfao","bdhgdekb","mggflahnoo","cmnol","imnmmgimmedf","mcjmoofomiia","mlakhbjfnbmgena","ilhmcnkkeg","domhbmkcd","fco",
				"fdio","cmkoagblnd","kmihfigmceiiicm","afgbadbgbaon","menahlemehifooe","jacokdiiokaic","limj","fkedaoomokjbkdi","jkncd",
				"jblmcmfnegnk","jjicjhjhbg","gbfcead","jf","aifnkmnao","effmhlhchngknl","odhjeib","ohcgmgb","bgbd","am","kkjfbdlh",
				"hgbjakkokjgooel","jbeokiakf","flaoba","cifcdnanmk","mice","ihhofdai","ldnfmeiemhf","kefbbohhgineacj","bi","njfie",
				"ociodahlomoekkf","andhoindeca","ajnndjocjeg","bmijkmjbbkgbbh","feanh","bjemcefkfcaenal","edfdenghinm","moal","ndbjdmijh",
				"enccnhmoifa","dbckadjibam","gd","oglj","aldjelhbemle","cmbkofkcoe","ihciacibeh","lcojkclhmibgoif","jfmjncnolfj",
				"gfcmcabhjki","aggfmakaanjb","mhbelld","hon","nkfoikcddehcah","kggbigknacmohb","jbkgndofcmaaohh","gkjano","afhhhh","mjng",
				"jilckm","dekkedjehmenbm","clfm","acmhbkdadgena","oenokachg","lhiea","dceiag","eebgj","oolifidh","dj","cdfn","eghdglgiok",
				"jdhegkefhbdhkm","mhgngafea","akabbcjkdnbc","gcbn","kimdgahf","oc"};//{"c","cat", "cats", "and", "sand", "dog","ats"};
         */
        List<String> s = new ArrayList<>(Arrays.asList("catsanddog","pineapplepenapple"));
        List<String[]> dict = new ArrayList<>();
        dict.add(new String[] { "cat", "cats", "and", "sand", "dog" });
        dict.add(new String[] { "apple", "pen", "applepen", "pine", "pineapple" });
        for (String t : new WordBreak2().findWords(s.get(0), dict.get(0))) {
            System.out.println(t);
        }
        for (String t : new WordBreak2().findWords(s.get(1), dict.get(1))) {
            System.out.println(t);
        }

    }
}