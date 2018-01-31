#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <queue>
#include <stdexcept>
#include <iostream>
#include <unordered_set>
#include <unordered_map>
#include <vector>

using namespace std;
        typedef struct _parent_node parent_node;
        struct _parent_node{
            string link_word;
            parent_node *next;
        };

        typedef struct {
            parent_node *parent;
            int level;
        } word_node;
class WordLadder {
    public:
        void free_res() {
            for(auto it = discovered.begin(); it != discovered.end(); ++it) {
                parent_node *parent = ((word_node*)it->second)->parent;
                parent_node *temp = parent;
                while (parent) {
                    parent = parent->next;
                    delete temp;
                    temp = parent;
                }
                delete it->second;
            }
            discovered.clear();
            //q = {};
            dict.clear();
        }

        vector<vector<string>> findLadders(string beginW, string endW, vector<string>& wordList) {
            beginWord = beginW;
            endWord = endW;
            WORD_LEN = beginWord.length();
            discovered.clear();
            //q = {};
            dict.clear();
            for (string word: wordList) {
                dict.insert(word);
            }
            dict.insert(endWord);
            word_node *begin_node = new word_node;
            begin_node->level = 0;
            begin_node->parent = NULL;
            discovered[beginWord] = begin_node;
            q.push(beginWord);

            while (!q.empty()) {
                string w = q.front();
                q.pop();
                if (discovered[w]->level >= max_level) {
                    break;
                } else {
                    nav_linked_words(w);
                }
            }

            cout << "start get path" << endl;
            vector<vector<string> > ret = get_paths(endWord);
            free_res();
            return ret;
        }
    private:
        queue<string> q;
        unordered_map<string, word_node*> discovered;
        unordered_set<string> dict;
        int max_level = 9999;
        string beginWord;
        string endWord;
        int WORD_LEN;

        bool match(string& a, string& w) {
            bool diff_found = false;
            for (int i = 0; i < a.length(); i++) {
                if (w[i] != a[i]) {
                    if(diff_found) {
                        return false;
                    }
                    diff_found = true;
                }
            }

            return true; //true only if there's only one difference
        };

        void nav_linked_words(string& word) {
            if (dict.size() < 1) {
            //  if (dict.size() < 26 * WORD_LEN) {
                //iterate through dict
                for(string key : dict)
                {
                    if (discovered.count(key) > 0 && discovered[key]->level <= discovered[word]->level) {
                        continue;
                    }
                    if (match(key, word)) {
                        word_node *node = discovered[key];
                        if (!node) {
                            node = new word_node;
                            node->parent = NULL;
                            discovered[key] = node;
                        }
                        node->level = discovered[word]->level + 1;
                        parent_node *parent = new parent_node;
                        parent->link_word = word;
                        parent->next = node->parent;
                        node->parent = parent;
                        //discovered[key] = node;
                        q.push(key);
                        if (key == endWord) {
                            max_level = node->level;
                            //*q = {};
                            //return;
                        }
                    }
                }
            } else {
                string t = word;
                for (int i = 0; i < WORD_LEN; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != word[i]) {
                            t[i] = c;
                            //t is the parent or sibling node
                            if (discovered.count(t) > 0 && discovered[t]->level <= discovered[word]->level) {
                                continue; 
                            }
                            if (dict.find(t) != dict.end()) {
                                word_node *node = discovered[t];
                                if (!node) {
                                    node = new word_node;
                                    node->parent = NULL;
                                    discovered[t] = node;
                                }
                                node->level = discovered[word]->level + 1;
                                parent_node *parent = new parent_node;
                                parent->link_word = word;
                                parent->next = node->parent;
                                node->parent = parent;
                                //discovered[t] = node;
                                q.push(t);
                                if (t == endWord) {
                                    max_level = node->level;
                                    //*q = {};
                                    //return;
                                }
                            }
                            t[i] = word[i];
                        }
                    }
                }
            }
            };

            vector<vector<string> > get_paths(string& word)
            {
                word_node *node = discovered[word];
                parent_node *parent = node->parent;
                vector<vector<string> > ret_paths;
                if (!parent) {
                    vector<string> path;
                    path.push_back(word);
                    ret_paths.push_back(path);
                } else {
                    while (parent) {
                        vector<vector<string> > paths = get_paths(parent->link_word);
                        for(vector<string> path: paths) {
                            path.push_back(word);
                            ret_paths.push_back(path);
                        }
                        parent = parent->next;
                    }
                }
                return ret_paths;
            };
        };

int main(int argc, char **argv)
{
    string beginWord = "hit";
    string endWord = "cog";
    vector<string> wordList = {"hot","dot","dog","lot","log","cog"};
    //Return
    //    [
    //    ["hit","hot","dot","dog","cog"],
    //    ["hit","hot","lot","log","cog"]
    //        ]
    WordLadder ladder;
    vector<vector<string> > paths = ladder.findLadders(beginWord, endWord, wordList);
    for (vector<string> path: paths) {
        for (string word : path) {
            cout << word << ", ";
        }
        cout << endl;
    }
    //need to release the parent node
    return EXIT_SUCCESS;
}


