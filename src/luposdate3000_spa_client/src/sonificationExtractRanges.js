function createOrAppend(arr, key, element) {
    if (key in arr) {
        arr[key].push(element)
    } else {
        arr[key] = [element]
    }
}

function reverseArray(src, dest) {
    for (const k in src) {
        for (const v of src[k]) {
            createOrAppend(dest, v, k)
        }
    }
}

function objectToArray(obj) {
    const res = []
    for (const k in obj) {
        const x = obj[k]
        if (Array.isArray(x)) {
            res[k] = x[0]
        } else {
            res[k] = x
        }
    }
    return res
}

function objectKeysToArray(obj) {
    const res = []
    for (const k in obj) {
        res.push(k)
    }
    return res
}

const sonificationKeys = ["Operator Types", "Operator IDs", "Operator Variables", "Operator Depths", "Data IDs", "Data Variables", "Query Progress"]

export function extractRanges(result, cacheGraph, sonificationRanges, sonificationRangesReverse) {
    for (const k of sonificationKeys) {
        sonificationRanges[k] = {}
        sonificationRangesReverse[k] = {}
    }
    sonificationRangesReverse["Operator Depths"][cacheGraph.nodes[0].id] = 0
    var changed = true
    while (changed) {
        changed = false
        for (const n of cacheGraph.edges) {
            if (n.from in sonificationRangesReverse["Operator Depths"] && (!(n.to in sonificationRangesReverse["Operator Depths"]))) {
                changed = true
                sonificationRangesReverse["Operator Depths"][n.to] = sonificationRangesReverse["Operator Depths"][n.from] + 1
            }
        }
    }
    const relevantNodes = {}
    for (const nn in result.animation) {
        const n = result.animation[nn]
        const l = n[2].split(" = ")
        relevantNodes[n[0]] = 1
        createOrAppend(sonificationRanges["Query Progress"], nn, nn)
        createOrAppend(sonificationRanges["Data IDs"], n[3], nn)
        createOrAppend(sonificationRanges["Data Variables"], l[0], nn)
    }
    for (const n in sonificationRangesReverse["Operator Depths"]) {
        const nn = sonificationRangesReverse["Operator Depths"][n]
        if (nn in relevantNodes) {
            createOrAppend(sonificationRanges["Operator Depths"], nn, n)
        } else {
            delete sonificationRangesReverse["Operator Depths"][n]
        }
    }
    for (const n of cacheGraph.nodes) {
        const l = n.label.split(' ')
        const l2 = l[1].split("\n")
        if (n.id in relevantNodes) {
            createOrAppend(sonificationRanges["Operator Types"], l[0], n.id)
            createOrAppend(sonificationRanges["Operator IDs"], l2[0], n.id)
            createOrAppend(sonificationRanges["Operator Variables"], l2[1], n.id)
        }
    }
    for (const k of sonificationKeys) {
if(k !="Operator Depths"){
        reverseArray(sonificationRanges[k], sonificationRangesReverse[k])
}
    }
    for (const cc of ["Operator Types", "Operator IDs", "Operator Variables", "Operator Depths"]) {
        const res = []
        for (const nn in result.animation) {
            const n = result.animation[nn]
            res[nn] = sonificationRangesReverse[cc][n[0]]
        }
        sonificationRangesReverse[cc] = res
    }
    for (const k of sonificationKeys) {
        sonificationRangesReverse[k] = objectToArray(sonificationRangesReverse[k])
        sonificationRanges[k] = objectKeysToArray(sonificationRanges[k])
    }
}
