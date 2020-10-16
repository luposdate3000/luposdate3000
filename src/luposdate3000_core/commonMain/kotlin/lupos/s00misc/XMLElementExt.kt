package lupos.s00misc
fun XMLElement.Companion.parseFromAny(data: String, filename: String): XMLElement? {
            when {
                filename.endsWith(".srx") -> {
                    return XMLElement.parseFromXml(data)
                }
                filename.endsWith(".tsv") -> {
                    return XMLElement.parseFromTsv(data)
                }
                filename.endsWith(".csv") -> {
                    return XMLElement.parseFromCsv(data)
                }
                filename.endsWith(".ttl") -> {
                    return XMLElement.parseFromTtl(data)
                }
                filename.endsWith(".nt") -> {
                    return XMLElement.parseFromTtl(data)
                }
                filename.endsWith(".n3") -> {
                    return XMLElement.parseFromTtl(data)
                }
                filename.endsWith(".srj") -> {
                    return XMLElement.parseFromJson(data)
                }
                filename.endsWith(".rdf") -> {
                    throw UnknownDataFile(filename)
                }
                else -> {
                    throw UnknownDataFile(filename)
                }
            }
        }
