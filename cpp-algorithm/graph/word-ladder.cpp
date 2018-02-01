#include <string>
#include <queue>
#include <iostream>
#include <unordered_set>
#include <unordered_map>
#include <algorithm>
#include <vector>

using namespace std;
class WordLadder {
    public:
        vector<vector<string>> findLadders(string beginWord, string endWord, vector<string>& wordList) {
            queue<string> q;
            unordered_map<string, int> level;
            unordered_map<string, vector<string>> parents;
            unordered_set<string> dict;

            for (string w: wordList) {
                dict.insert(w);
            }
            dict.insert(endWord);

            level[beginWord] = 0;
            q.push(beginWord);

            bool found = false;
            while (!q.empty()) {
                string word = q.front();
                q.pop();
                string tmp = word;
                for (int i = 0; i < word.size(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == word[i]) continue;

                        tmp[i] = c;
                        if (dict.find(tmp) != dict.end()) {
                            if (level.count(tmp) > 0 && level[tmp] <= level[word] || (found && tmp != endWord)) {
                                tmp[i] = word[i];
                                continue;
                            }

                            if (parents.count(tmp) == 0) {
                                q.push(tmp);
                                level[tmp] = level[word]+1;
                                if (tmp == endWord) {
                                    found = true;
                                }
                            }
                            parents[tmp].push_back(word);
                            cout << "get parent " <<word << " for " << tmp << endl;
                        }
                        tmp[i] = word[i];
                    }
                }
            }

            vector<vector<string> > ret;
            vector<string> path;
            path.push_back(endWord);
            getPaths(endWord, level, parents, path, ret);
            return ret;
        };
    private:
        void getPaths(string& word, unordered_map<string, int>& level, unordered_map<string, vector<string> >& parent, vector<string>& path, vector<vector<string>>& ret)
        {
            if (level[word] == 0) {
                reverse(path.begin(), path.end());
                ret.push_back(path);
                reverse(path.begin(), path.end());
            } else {
                for(int i = 0; i < parent[word].size(); i++) {
                    path.push_back(parent[word][i]);
                    getPaths(parent[word][i], level, parent, path, ret);
                    path.pop_back();
                }
            }
        };
};

int main(int argc, char **argv)
{

    //        bool match(string& a, string& w) {
    //                bool diff_found = false;
    //                for (int i = 0; i < a.length(); i++) {
    //                if (w[i] != a[i]) {
    //                    if(diff_found) {
    //                        return false;
    //                    }
    //                    diff_found = true;
    //                }
    //            }
    //
    //            return true; //true only if there's only one difference
    //        };
    //

    //                if (dict.size() < 1) {
    //                //if (dict.size() < 26 * WORD_LEN) {
    //                    //iterate through dict
//                    for(string tmp : dict)
//                    {
//                        if (level.count(tmp) > 0 && level[tmp] <= level[word])
//                            continue;
//
//                        if (match(tmp, word) && (!found || tmp == endWord)) {
//                            parents[tmp].push_back(word);
//                            if (level.count(tmp) == 0) {
//                                level[tmp] = level[word]+1;
//                                cout << "get parent " <<word << "(" << level[word] << ") for " << tmp << "(" << level[tmp] << ")" << endl;
//                                q.push(tmp);
//                                if (tmp == endWord) {
//                                    found = true;
//                                }
//                            }
//                        }
//                    }
//                } else {
//
    string beginWord = "hit";
    string endWord = "cog";
    vector<string> wordList = {"hot","dot","dog","lot","log","cog", "dit"};
    //Return
    //    [
    //    ["hit","hot","dot","dog","cog"],
    //    ["hit","hot","lot","log","cog"]
    //    ["hit","dit","dot","dog","cog"]
    //        ]
    WordLadder ladder;
    vector<vector<string> > paths = ladder.findLadders(beginWord, endWord, wordList);
    
    for (vector<string> path: paths) {
        for (string word : path) {
            cout << word << ", ";
        }
        cout << endl;
    }
    return EXIT_SUCCESS;
}


