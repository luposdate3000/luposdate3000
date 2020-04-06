cat PatriciaTrieTest.kts > tmp
cat src/commonMain/kotlin/lupos/s03resultRepresentation/PatriciaTrie.kt >> tmp
cat src/commonMain/kotlin/lupos/s03resultRepresentation/PatriciaTrieTest.kt >> tmp
cat tmp | grep -v package >log/script.kts
chmod +x log/script.kts
kotlinc -script log/script.kts
