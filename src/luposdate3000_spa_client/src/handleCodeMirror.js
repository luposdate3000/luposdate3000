import {
    basicSetup
} from 'codemirror';
import {
    EditorState
} from "@codemirror/state";
import {
    EditorView,
    lineNumbers
} from "@codemirror/view";
import {
    sparql
} from 'codemirror-lang-sparql';
import {
    turtle
} from 'codemirror-lang-turtle';

var codeMirrorHelper = {}

function enableCodeMirror(prefix, lang) {
    const editorSource = document.querySelector("#" + prefix + "EditorSource");
    const editorTarget = document.querySelector("#" + prefix + "EditorTarget");
    editorSource.setAttribute("hidden", "true");
    const startState = EditorState.create({
        doc: editorSource.value,
        extensions: [basicSetup, lineNumbers(), lang],
    });
    const view = new EditorView({
        state: startState,
        parent: editorTarget,
    });
    codeMirrorHelper[prefix] = {
        "view": view
    }
}
enableCodeMirror("sparql", sparql())
enableCodeMirror("rdf", turtle())

function setCode(prefix, data) {
    const view = codeMirrorHelper[prefix].view
    view.dispatch({
        changes: {
            from: 0,
            to: view.state.doc.length,
            insert: data
        }
    })
}

function getCode(prefix) {
    return codeMirrorHelper[prefix].view.state.doc.toString()
}
export function setSparql(data) {
    setCode("sparql", data)
}
export function setRDF(data) {
    setCode("rdf", data)
}
export function getSparql() {
    return getCode("sparql")
}
export function getRDF() {
    return getCode("rdf")
}