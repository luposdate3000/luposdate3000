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

export function extractRanges(result,cacheGraph, sonificationRanges, sonificationRangesReverse) {
    sonificationRanges.operator = {
        "types": {},
        "ids": {},
        "variables": {},
        "depths": {}
    }
    sonificationRanges.query = {
        "progress": {}
    }
    sonificationRanges.data = {
        "ids": {},
        "variables": {}
    }
    sonificationRangesReverse.operator = {
        "types": {},
        "ids": {},
        "variables": {},
        "depths": {}
    }
    sonificationRangesReverse.query = {
        "progress": {}
    }
    sonificationRangesReverse.data = {
        "ids": {},
        "variables": {}
    }
sonificationRangesReverse.operator.depths[cacheGraph.nodes[0].id] = 0
var changed = true
        while (changed) {
            changed = false
            for (const n of cacheGraph.edges) {
                if (n.from in sonificationRangesReverse.operator.depths && (!(n.to in sonificationRangesReverse.operator.depths))) {
                    changed = true
                    sonificationRangesReverse.operator.depths[n.to] = sonificationRangesReverse.operator.depths[n.from] + 1
                }
            }
        }
        const relevantNodes = {}
        for (const nn in result.animation) {
            const n = result.animation[nn]
            const l = n[2].split(" = ")
            relevantNodes[n[0]] = 1
            createOrAppend(sonificationRanges.query.progress, nn, nn)
            createOrAppend(sonificationRanges.data.ids, n[3], nn)
            createOrAppend(sonificationRanges.data.variables, l[0], nn)
        }
 for (const n in sonificationRangesReverse.operator.depths) {
            const nn = sonificationRangesReverse.operator.depths[n]
            if (nn in relevantNodes) {
                createOrAppend(sonificationRanges.operator.depths, nn, n)
            } else {
                delete sonificationRangesReverse.operator.depths[n]
            }
        }

        for (const n of cacheGraph.nodes) {
            const l = n.label.split(' ')
            const l2 = l[1].split("\n")
            if (n.id in relevantNodes) {
                createOrAppend(sonificationRanges.operator.types, l[0], n.id)
                createOrAppend(sonificationRanges.operator.ids, l2[0], n.id)
                createOrAppend(sonificationRanges.operator.variables, l2[1], n.id)
            }
        }
        reverseArray(sonificationRanges.operator.types, sonificationRangesReverse.operator.types)
        reverseArray(sonificationRanges.operator.ids, sonificationRangesReverse.operator.ids)
        reverseArray(sonificationRanges.operator.variables, sonificationRangesReverse.operator.variables)
        reverseArray(sonificationRanges.data.variables, sonificationRangesReverse.data.variables)
        reverseArray(sonificationRanges.data.ids, sonificationRangesReverse.data.ids)
        reverseArray(sonificationRanges.query.progress, sonificationRangesReverse.query.progress)
        for (const cc of ["variables", "ids", "types", "depths"]) {
            const res = []
            for (const nn in result.animation) {
                const n = result.animation[nn]
                res[nn] = sonificationRangesReverse.operator[cc][n[0]]
            }
            sonificationRangesReverse.operator[cc] = res
        }
sonificationRangesReverse.operator.ids = objectToArray(sonificationRangesReverse.operator.ids)
        sonificationRangesReverse.operator.types = objectToArray(sonificationRangesReverse.operator.types)
        sonificationRangesReverse.operator.variables = objectToArray(sonificationRangesReverse.operator.variables)
        sonificationRangesReverse.operator.depths = objectToArray(sonificationRangesReverse.operator.depths)
        sonificationRangesReverse.data.ids = objectToArray(sonificationRangesReverse.data.ids)
        sonificationRangesReverse.data.variables = objectToArray(sonificationRangesReverse.data.variables)
        sonificationRangesReverse.query.progress = objectToArray(sonificationRangesReverse.query.progress)

        sonificationRanges.operator.ids = objectKeysToArray(sonificationRanges.operator.ids)
        sonificationRanges.operator.types = objectKeysToArray(sonificationRanges.operator.types)
        sonificationRanges.operator.variables = objectKeysToArray(sonificationRanges.operator.variables)
        sonificationRanges.operator.depths = objectKeysToArray(sonificationRanges.operator.depths)
        sonificationRanges.data.ids = objectKeysToArray(sonificationRanges.data.ids)
        sonificationRanges.data.variables = objectKeysToArray(sonificationRanges.data.variables)
        sonificationRanges.query.progress = objectKeysToArray(sonificationRanges.query.progress)
}
