# Viikkoraportti 4

Tällä viikolla olen pääasiassa työskennellyt Huffman code -algoritmin parissa. Toteutukseni ei kuitenkaan vielä näytä toimivan niin kuin sen pitäisi. Enkoodaus-dekoodaus -operaation tuloksena on kyllä oikeat merkit, mutta ne ovat väärässä järjestyksessä ja kukin merkki esiintyy vain kerran vaikka toistuvia merkkejä syötteessä olisikin. 

Esimerkiksi merkkijonon "abc" tuloksena on "bac" ja merkkijonon "test" taas "tes". Pidemmissä syötteissä merkit ovat lopulta täysin sekaisin.

Olen kokeillut kaikenlaista säätöä, mutta mikään ei ole toiminut. Olen tämän kanssa nyt jumissa. Yritän taas ensi viikolla etsiä apua internetin syövereistä ja tutkin jos Javan PriorityQueuen korvaaminen omalla tietorakenteellani auttaisi asiaa.

Viikon läpimurtona olen ymmärtänyt LZW-pakkausongelmani syyn, tai ainakin luulisin niin. Tallennan kaikki merkit 32 bittisenä, joten tottakai tiedoston koko paisuu suureksi. Testasin tallentamalla 2 tavua per merkki, jolloin tulos on oikea ja tiedoston koko on pienempi kuin lähdetiedosto. En ole vielä tosin aloittanut korjaamaan tätä.

Jos Huffman-debuggaamisesta jää aikaa ensi viikolla, pyrin lisäämään yksikkötestien kattavuutta siten, että myös ns. edge caset tulee otettua huomioon. 

## Käytetyt tunnit

Tällä viikolla olen käyttänyt noin 9 tuntia tähän työhön.