fun add_elements(to : MutableList <Int>, from : MutableList <Int>) {
    for (x in from) to.add(x);
}
fun print_vec(vec : MutableList <Int>) {
    for (x in vec) {
        print(x);
        print(" ");
    }
    println("");
}

class lovely_bor() {
    class node() {
        val to = mutableMapOf<Char, Int>();
        val term_inds = mutableListOf <Int>();
    }

    private val bor = mutableListOf <node> ();

    fun add_node(parent_ind : Int, rebro : Char) {
        bor.add(node());
        bor[parent_ind].to[rebro] = bor.size - 1;
    }

    fun add_string(text : String, start_ind : Int) {
        var node_ind = 1; // root ind

        for (i in start_ind..<(text.length)) {
            val a = text[i];
            if (bor[node_ind].to.get(a) == null) {
                add_node(node_ind, a);
            }

            node_ind = bor[node_ind].to.get(a)!!;
            bor[node_ind].term_inds.add(start_ind);
        }
    }

    fun build(file_text : String) {
        bor.clear();

        bor.add(node()); // fictive node
        bor.add(node()); // root node
        for (i in 0..<(file_text.length)) {
            add_string(file_text, i);
        }
    }

    fun find_string(s : String) : MutableList <Int> {
        val inds = mutableListOf <Int> ();
        var i = 0;
        var node_ind = 1;

        while (i < s.length) {
            if (bor[node_ind].to[s[i]] == null) {
                node_ind = -1;
                break;
            }

            val it = bor[node_ind].to[s[i]]!!;

            node_ind = it;
            i++;
        }
        if (node_ind != -1) add_elements(inds, bor[node_ind].term_inds);

        return inds;
    }
}

fun main() {
    val bor = lovely_bor();

    val file_text = "aa";

    bor.build(file_text);

    val wanted = "aa";

    print_vec(bor.find_string(wanted));
}
