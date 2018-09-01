import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProductIm } from 'app/shared/model/product-im.model';
import { ProductImService } from './product-im.service';
import { ProductImComponent } from './product-im.component';
import { ProductImDetailComponent } from './product-im-detail.component';
import { ProductImUpdateComponent } from './product-im-update.component';
import { ProductImDeletePopupComponent } from './product-im-delete-dialog.component';
import { IProductIm } from 'app/shared/model/product-im.model';

@Injectable({ providedIn: 'root' })
export class ProductImResolve implements Resolve<IProductIm> {
    constructor(private service: ProductImService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((product: HttpResponse<ProductIm>) => product.body));
        }
        return of(new ProductIm());
    }
}

export const productRoute: Routes = [
    {
        path: 'product-im',
        component: ProductImComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.product.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-im/:id/view',
        component: ProductImDetailComponent,
        resolve: {
            product: ProductImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.product.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-im/new',
        component: ProductImUpdateComponent,
        resolve: {
            product: ProductImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.product.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-im/:id/edit',
        component: ProductImUpdateComponent,
        resolve: {
            product: ProductImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.product.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const productPopupRoute: Routes = [
    {
        path: 'product-im/:id/delete',
        component: ProductImDeletePopupComponent,
        resolve: {
            product: ProductImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.product.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
