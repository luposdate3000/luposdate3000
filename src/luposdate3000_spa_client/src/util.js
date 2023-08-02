export function download(content, mimeType, filename) {
    const a = document.createElement('a')
    const blob = new Blob([content], {
        type: mimeType
    })
    const url = URL.createObjectURL(blob)
    a.setAttribute('href', url)
    a.setAttribute('download', filename)
    a.click()
}
export const visNetworkOptions = {
    autoResize: false,
    nodes: {
        physics: false,
        shape: "box",
        font: {
            size: 14,
            color: "#000000",
            face: "Ubuntu",
        },
        borderWidth: 1,
        color: {
            background: "#E1E1E1",
            border: "#000000",
            hover: {
                background: "#C5C5C5",
                border: "#000000",
            },
            highlight: {
                background: "#C5C5C5",
                border: "#000000",
            },

        },

    },
    edges: {
        smooth: false
    },
    physics: {
        enabled: true,
        hierarchicalRepulsion: {
            nodeDistance: 200,
            avoidOverlap: 1
        },
    },
    layout: {
        improvedLayout: true,
        hierarchical: {
            levelSeparation: 100,
            nodeSpacing: 300,
            treeSpacing: 200,
        }
    },
    interaction: {
        hover: true
    }
};