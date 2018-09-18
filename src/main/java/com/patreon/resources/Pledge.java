package com.patreon.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Type("pledge")
public class Pledge extends BaseResource {
  
  public enum PledgeField implements Field {
    AmountCents("amount_cents", true),
    CreatedAt("created_at", true),
    DeclinedSince("declined_since", true),
    PatronPaysFees("patron_pays_fees", true),
    PledgeCapCents("pledge_cap_cents", true),
    TotalHistoricalAmountCents("total_historical_amount_cents", false),
    IsPaused("is_paused", false),
    HasShippingAddress("has_shipping_address", false),
    ;

    private final String propertyName;
    private final boolean isDefault;

    PledgeField(String propertyName, boolean isDefault) {
      this.propertyName = propertyName;
      this.isDefault = isDefault;
    }

    public static Collection<PledgeField> getDefaultFields() {
      return Arrays.stream(values()).filter(Field::isDefault).collect(Collectors.toList());
    }

    @Override
    public String getPropertyName() {
      return this.propertyName;
    }

    @Override
    public boolean isDefault() {
      return this.isDefault;
    }
  }

  private int amount_cents;
  private String created_at;
  private String declined_since;
  private boolean patron_pays_fees;
  private int pledge_cap_cents;

  //Optional properties.  Will be null if not requested
  private Integer total_historical_amount_cents;
  private Boolean is_paused;
  private Boolean has_shipping_address;

  @Relationship("creator")
  private User creator;

  @Relationship("patron")
  private User patron;

  @Relationship("reward")
  private Reward reward;

  public Pledge(
                 @JsonProperty("amount_cents") int amount_cents,
                 @JsonProperty("created_at") String created_at,
                 @JsonProperty("declined_since") String declined_since,
                 @JsonProperty("patron_pays_fees") boolean patron_pays_fees,
                 @JsonProperty("pledge_cap_cents") int pledge_cap_cents,
                 @JsonProperty("total_historical_amount_cents") Integer total_historical_amount_cents,
                 @JsonProperty("is_paused") Boolean is_paused,
                 @JsonProperty("has_shipping_address") Boolean has_shipping_address,
                 @JsonProperty("creator") User creator,
                 @JsonProperty("patron") User patron,
                 @JsonProperty("reward") Reward reward
  ) {
    this.amount_cents = amount_cents;
    this.created_at = created_at;
    this.declined_since = declined_since;
    this.patron_pays_fees = patron_pays_fees;
    this.pledge_cap_cents = pledge_cap_cents;
    this.total_historical_amount_cents = total_historical_amount_cents;
    this.is_paused = is_paused;
    this.has_shipping_address = has_shipping_address;
    this.creator = creator;
    this.patron = patron;
    this.reward = reward;
  }

  public int getAmountCents() {
    return amount_cents;
  }

  public String getCreatedAt() {
    return created_at;
  }

  public String getDeclinedSince() {
    return declined_since;
  }

  public boolean getPatronPaysFees() {
    return patron_pays_fees;
  }

  public int getPledgeCapCents() {
    return pledge_cap_cents;
  }

  /**
   * @return The lifetime value this patron has paid to the campaign, or null
   * if this field was not requested
   */
  public Integer getTotalHistoricalAmountCents() {
    return total_historical_amount_cents;
  }

  /**
   * @return Whether the pledge is paused, or null if this field wasn't requested.
   */
  public Boolean getPaused() {
    return is_paused;
  }

  /**
   * @return Whether this patron has a shipping address, or null if this field wasn't requested
   */
  public Boolean getHasShippingAddress() {
    return has_shipping_address;
  }

  public User getCreator() {
    return creator;
  }

  public User getPatron() {
    return patron;
  }

  public Reward getReward() {
    return reward;
  }
}
