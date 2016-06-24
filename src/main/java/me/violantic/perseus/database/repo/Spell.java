package me.violantic.perseus.database.repo;

import me.violantic.perseus.database.repo.datatype.PerseusSpell;
import me.violantic.perseus.database.repo.util.PerseusSpellUtil;

/**
 * Created by Ethan on 6/23/2016.
 */
public enum Spell {

    ACCIO(PerseusSpellUtil.newInstance("accio")),
    Aguamenti(PerseusSpellUtil.newInstance("aguamenti")),
    Apparate(PerseusSpellUtil.newInstance("apparate")),
    AraniaExumai(PerseusSpellUtil.newInstance("araniaexumai")),
    AvadaKedavra(PerseusSpellUtil.newInstance("avadakedavra")),
    Bombarda(PerseusSpellUtil.newInstance("bombarda")),
    Confringo(PerseusSpellUtil.newInstance("confringo")),
    Confundus(PerseusSpellUtil.newInstance("confundus")),
    Conjunctivitus(PerseusSpellUtil.newInstance("conjunctivitus")),
    Crucio(PerseusSpellUtil.newInstance("crucio")),
    Ebublio(PerseusSpellUtil.newInstance("ebublio")),
    Expelliarmus(PerseusSpellUtil.newInstance("expelliarmus")),
    Expulso(PerseusSpellUtil.newInstance("expulso")),
    Fiendfyre(PerseusSpellUtil.newInstance("fiendfyre")),
    Firestorm(PerseusSpellUtil.newInstance("firestorm")),
    Flipendo(PerseusSpellUtil.newInstance("flipendo")),
    Glacius(PerseusSpellUtil.newInstance("glacius")),
    Incendio(PerseusSpellUtil.newInstance("incendio")),
    Levicorpus(PerseusSpellUtil.newInstance("levicorpus")),
    Lumos(PerseusSpellUtil.newInstance("lumos")),
    Obscuro(PerseusSpellUtil.newInstance("obscuro")),
    Protego(PerseusSpellUtil.newInstance("protego")),
    Reducto(PerseusSpellUtil.newInstance("reducto")),
    Sectumsempra(PerseusSpellUtil.newInstance("sectumsempra")),
    Stupefy(PerseusSpellUtil.newInstance("stupefy")),
    ;

    PerseusSpell origin;

    Spell(PerseusSpell spellOrigin) {
        this.origin = spellOrigin;
    }

    public static boolean isSpell(String spell) {
        for(Spell s : values()) {
            if(s.toString().toLowerCase().contains(spell.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

}
