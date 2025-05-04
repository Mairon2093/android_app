#include <iostream>
#include <vector>
#include <map>

using namespace std;

void add_elements(vector <int>& to, vector <int>& from) {
    for (int x : from) to.push_back(x);
}
void print(const vector <int>& vec) {
    for (int x : vec) cout << x << " ";
    cout << "\n";
}

class lovely_bor {

private:
    struct node {
        map <char, int> to;
        vector <int> term_inds;
    };

    vector <node> bor;

public:
    void add_node(int parent_ind, char rebro) {
        bor.push_back(node());
        bor[parent_ind].to[rebro] = bor.size() - 1;
    }

    void add_string(const string& text, int start_ind) {
        int node_ind = 1; // root ind

        for (int i = start_ind; i < text.size(); i++) {
            char a = text[i];
            if (bor[node_ind].to[a] == 0) {
                add_node(node_ind, a);
            }

            //cout << start_ind << ": from " << node_ind << " to ";
            node_ind = bor[node_ind].to[a];
            //cout << node_ind << endl;
            bor[node_ind].term_inds.push_back(start_ind);
        }
    }

    void build(const string& file_text) {
        bor.clear();

        bor.push_back(node()); // fictive node
        bor.push_back(node()); // root node
        for (int i = 0; i < file_text.size(); i++) {
            add_string(file_text, i);
        }
    }

    vector <int> find_string(const string& s) {
        vector <int> inds;
        int i = 0, node_ind = 1;

        while (i < s.size()) {
            int it = bor[node_ind].to[s[i]];
            if (it == 0) {
                node_ind = -1;
                break;
            }
            node_ind = it;
            i++;
        }
        if (node_ind != -1) add_elements(inds, bor[node_ind].term_inds);

        return inds;
    }
};

int main() {
    lovely_bor bor;

    string file_text = "ba";

    bor.build(file_text);

    string wanted = "a";

    print(bor.find_string(wanted));
}
