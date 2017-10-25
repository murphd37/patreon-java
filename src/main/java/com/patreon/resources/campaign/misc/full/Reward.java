package com.patreon.resources.campaign.misc.full;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.campaign.misc.simple.SimpleCampaign;
import com.patreon.resources.campaign.misc.simple.SimpleCreator;
import com.patreon.resources.shared.RelationshipsModel;

@Type("reward")
public class Reward extends RelationshipsModel {
    private String type;

    public String getType() {
        return type;
    }
    @Meta
    private RewardData attributes;

    public SimpleCreator getSimpleCreator() {
        return getRelationship("creator", SimpleCreator.class);
    }

    public SimpleCampaign getSimpleCampaign() {
        return getRelationship("campaign", SimpleCampaign.class);
    }

    public RewardData getAttributes() {
        return attributes;
    }
}