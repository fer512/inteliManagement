import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProductIm } from 'app/shared/model/product-im.model';
import { ProductImService } from './product-im.service';
import { IProviderIm } from 'app/shared/model/provider-im.model';
import { ProviderImService } from 'app/entities/provider-im';

@Component({
    selector: 'jhi-product-im-update',
    templateUrl: './product-im-update.component.html'
})
export class ProductImUpdateComponent implements OnInit {
    private _product: IProductIm;
    isSaving: boolean;

    providers: IProviderIm[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private productService: ProductImService,
        private providerService: ProviderImService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ product }) => {
            this.product = product;
        });
        this.providerService.query().subscribe(
            (res: HttpResponse<IProviderIm[]>) => {
                this.providers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.product.id !== undefined) {
            this.subscribeToSaveResponse(this.productService.update(this.product));
        } else {
            this.subscribeToSaveResponse(this.productService.create(this.product));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProductIm>>) {
        result.subscribe((res: HttpResponse<IProductIm>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProviderById(index: number, item: IProviderIm) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get product() {
        return this._product;
    }

    set product(product: IProductIm) {
        this._product = product;
    }
}
