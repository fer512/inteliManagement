import { Moment } from 'moment';
import { IProviderIm } from 'app/shared/model//provider-im.model';
import { IProductIm } from 'app/shared/model//product-im.model';

export interface IVariation {
    id?: number;
    extra_charge?: number;
    new_charge?: number;
    new_cost?: number;
    new_benefit?: number;
    new_external_locator_id?: number;
    comments?: string;
    creation_date?: Moment;
    creation_user?: string;
    provider?: string;
    product?: number;
    area?: string;
    campaing?: string;
    reason?: string;
    recoverable?: boolean;
    refund_in_points?: number;
    refund_in_cash?: number;
    cacel?: boolean;
    relationship_user_variationLogin?: string;
    relationship_user_variationId?: number;
    relationship_provider_variations?: IProviderIm[];
    relationship_product_variations?: IProductIm[];
}

export class Variation implements IVariation {
    constructor(public product?: number) {
        this.product = product;
    }
}
