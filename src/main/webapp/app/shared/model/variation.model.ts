import { Moment } from 'moment';
import { IProviderIm } from 'app/shared/model//provider-im.model';
import { IProductIm } from 'app/shared/model//product-im.model';
import { IApprovalsIm } from 'app/shared/model/approvals-im.model';

export interface IVariation {
    id?: number;
    extraCharge?: number;
    newCharge?: number;
    newCost?: number;
    newBenefit?: number;
    newExternalLocatorId?: number;
    comments?: string;
    creationDate?: Moment;
    creationUser?: string;
    provider?: string;
    product?: number;
    area?: string;
    campaing?: string;
    reason?: string;
    recoverable?: boolean;
    refundInPoints?: number;
    refundInCash?: number;
    cacel?: boolean;
    approvals?: IApprovalsIm;
}

export class Variation implements IVariation {
    constructor(public product?: number) {
        this.product = product;
    }
}
