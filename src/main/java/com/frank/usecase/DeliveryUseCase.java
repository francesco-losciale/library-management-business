/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2018
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.frank.usecase;

import com.frank.entity.courier.dto.ShipmentDetails;
import com.frank.entity.order.Order;

public class DeliveryUseCase {

    public void send(ShipmentDetails shipmentDetails, Order order) {
        order.deliver(shipmentDetails);
    }
}
