const jquery = require("jquery")
const randomColor = require('randomcolor');

function resultXMLToHTMLUri(uri, namespaces, colors, target) {
    for (var k in namespaces) {
        const v = namespaces[k];
        if (uri.startsWith(v)) {
            const span = document.createElement('span');
            span.appendChild(document.createTextNode(k + ":"));
            span.style.color = colors[k];
            target.appendChild(span);
            target.appendChild(document.createTextNode(uri.substring(v.length)));
            return
        }
    }
    target.appendChild(document.createTextNode("<" + uri + ">"));
}

function resultXMLToHTML(data, namespaces) {
    const div = document.createElement('div');
    const tbl = document.createElement('table');
    const tblh = document.createElement('thead');
    const tblb = document.createElement('tbody');
    const tblhr = tblh.insertRow();
    for (var xmlsparql of jquery.parseXML(data).getElementsByTagName("sparql")) {
        const boolResult = xmlsparql.getElementsByTagName("boolean")
        if (boolResult.length > 0) {
            const th = document.createElement('th');
            th.appendChild(document.createTextNode("boolean"));
            tblhr.appendChild(th);
            const tr = tblb.insertRow();
            const td = tr.insertCell();
            td.appendChild(document.createTextNode(boolResult[0].textContent))
        } else {
            const tbln = document.createElement('table');
            const colors = {};
            for (var k in namespaces) {
                const color = randomColor({
                    luminosity: 'dark'
                });
                colors[k] = color;
                const v = namespaces[k];
                const tr = tbln.insertRow();
                const td1 = tr.insertCell();
                td1.appendChild(document.createTextNode(k));
                td1.style.color = color;
                const td2 = tr.insertCell();
                td2.appendChild(document.createTextNode(v));
            }
            const h4 = document.createElement('h4');
            h4.appendChild(document.createTextNode("Prefixes"));
            div.appendChild(h4);
            div.appendChild(tbln);
            const columnNames = [];
            for (var xmlhead of xmlsparql.getElementsByTagName("head")) {
                for (var xmlvariable of xmlsparql.getElementsByTagName("variable")) {
                    const th = document.createElement('th');
                    const name = xmlvariable.getAttribute("name");
                    columnNames.push(name);
                    th.appendChild(document.createTextNode(name));
                    tblhr.appendChild(th);
                }
            }
            for (var xmlresults of xmlsparql.getElementsByTagName("results")) {
                for (var xmlresult of xmlresults.getElementsByTagName("result")) {
                    const tmpRow = {}
                    for (var xmlbinding of xmlresult.getElementsByTagName("binding")) {
                        for (var child of xmlbinding.children) {
                            const div2 = document.createElement('div');
                            if (child.nodeName === "uri") {
                                resultXMLToHTMLUri(child.textContent, namespaces, colors, div2)
                            } else if (child.nodeName === "bnode") {
                                div2.appendChild("_:" + document.createTextNode(child.textContent))
                            } else if (child.nodeName === "literal") {
                                if (child.hasAttribute("datatype")) {
                                    div2.appendChild(document.createTextNode("\"" + child.textContent + "\"^^"));
                                    resultXMLToHTMLUri(child.getAttribute("datatype"), namespaces, colors, div2)
                                } else if (child.hasAttribute("xml:lang")) {
                                    div2.appendChild(document.createTextNode("\"" + child.textContent + "\"@" + child.getAttribute("xml:lang")))
                                } else {
                                    div2.appendChild(document.createTextNode(child.textContent))
                                }
                            } else {
                                console.log("unknown nodeName:", child.nodeName, child)
                            }
                            tmpRow[xmlbinding.getAttribute("name")] = div2
                        }
                    }
                    const tr = tblb.insertRow();
                    for (name of columnNames) {
                        const td = tr.insertCell();
                        if (name in tmpRow) {
                            td.appendChild(tmpRow[name])
                        }
                    }
                }
            }
        }
    }
    tbl.appendChild(tblh);
    tbl.appendChild(tblb);
    const h4 = document.createElement('h4');
    h4.appendChild(document.createTextNode("Results(" + tblb.getElementsByTagName("tr").length + ")"));
    div.appendChild(h4);
    div.appendChild(tbl);
    return div
}

function createDownloadResultButtonsH(target, name, func) {
    const b1 = document.createElement('button');
    b1.classList.add("btn")
    b1.classList.add("btn-primary")
    b1.type = "button"
    b1.textContent = name
    b1.onclick = func
    target.appendChild(b1);
}

function createDownloadResultButtons(result) {
    const div = document.createElement('div');
    createDownloadResultButtonsH(div, "Download HTML",
        function() {
            const html = document.createElement('html');
            const head = document.createElement('head');
            const body = document.createElement('body');
            html.appendChild(head)
            const link = document.createElement('link');
            link.rel = "stylesheet"
            link.href = window.location.href + "/html_result_style.css"
            head.appendChild(link)
            html.appendChild(body)
            body.appendChild(result.html.cloneNode())
            download(html.outerHTML, "text/html", "result.html")
        });
    createDownloadResultButtonsH(div, "Download XML",
        function() {
            download(result.xml, "application/sparql‑results+xml", "result.xml")
        });
    return div
}

function download(content, mimeType, filename) {
    const a = document.createElement('a')
    const blob = new Blob([content], {
        type: mimeType
    })
    const url = URL.createObjectURL(blob)
    a.setAttribute('href', url)
    a.setAttribute('download', filename)
    a.click()
}
export function updateResultTab(result) {
    result.html = resultXMLToHTML(result.xml, result.namespaces)
    jquery("#result").empty();
    const target = document.getElementById('result');
    target.appendChild(createDownloadResultButtons(result))
    target.appendChild(result.html);
}