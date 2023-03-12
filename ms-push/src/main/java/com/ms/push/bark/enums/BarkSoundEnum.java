/*
 * @MS 2022-12-13
 * Copyright (c) 2001-2023 萌森
 * 保留所有权利
 * 本软件为萌森工作室所有及包含机密信息，须遵守其相关许可证条款进行使用。
 * Copyright (c) 2001-2023 Meng Sen
 * All rights reserved
 * This software is owned by Mengsen Studio and contains confidential information, and must be used in accordance with its relevant license terms.
 * Website：https://qyg2297248353.top
 */

package com.ms.push.bark.enums;

/**
 * 推送铃声
 *
 * @author qyg2297248353
 */

public enum BarkSoundEnum {
    /**
     * alarm
     */
    ALARM("alarm.caf"),
    /**
     * anticipate
     */
    ANTICIPATE("anticipate.caf"),
    /**
     * bell
     */
    BELL("bell.caf"),
    /**
     * birdsong
     */
    BIRDSONG("birdsong.caf"),
    /**
     * bloom
     */
    BLOOM("bloom.caf"),
    /**
     * calypso
     */
    CALYPSO("calypso.caf"),
    /**
     * chime
     */
    CHIME("chime.caf"),
    /**
     * choo
     */
    CH_OO("choo.caf"),
    /**
     * descent
     */
    DESCENT("descent.caf"),
    /**
     * electronic
     */
    ELECTRONIC("electronic.caf"),
    /**
     * fanfare
     */
    FANFARE("fanfare.caf"),
    /**
     * glass
     */
    GLASS("glass.caf"),
    /**
     * gotosleep
     */
    GOTO_SLEEP("gotosleep.caf"),
    /**
     * healthnotification
     */
    HEALTH_NOTIFICATION("healthnotification.caf"),
    /**
     * horn
     */
    HORN("horn.caf"),
    /**
     * ladder
     */
    LADDER("ladder.caf"),
    /**
     * mailsent
     */
    MAIL_SENT("mailsent.caf"),
    /**
     * minuet
     */
    MINUET("minuet.caf"),
    /**
     * multiwayinvitation
     */
    MULTIWAY_INVITATION("multiwayinvitation.caf"),
    /**
     * newmail
     */
    NEW_MAIL("newmail.caf"),
    /**
     * newsflash
     */
    NEWSFLASH("newsflash.caf"),
    /**
     * noir
     */
    NOIR("noir.caf"),
    /**
     * paymentsuccess
     */
    PAYMENT_SUCCESS("paymentsuccess.caf"),
    /**
     * shake
     */
    SHAKE("shake.caf"),
    /**
     * sherwoodforest
     */
    SHERWOOD_FOREST("sherwoodforest.caf"),
    /**
     * silence
     */
    SILENCE("silence.caf"),
    /**
     * spell
     */
    SPELL("spell.caf"),
    /**
     * suspense
     */
    SUSPENSE("suspense.caf"),
    /**
     * telegraph
     */
    TELEGRAPH("telegraph.caf"),
    /**
     * tiptoes
     */
    TIPTOES("tiptoes.caf"),
    /**
     * typewriters
     */
    TYPEWRITERS("typewriters.caf"),
    /**
     * update
     */
    UPDATE("update.caf");


    private String name;

    BarkSoundEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
