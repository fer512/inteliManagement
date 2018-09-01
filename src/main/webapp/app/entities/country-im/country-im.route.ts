import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CountryIm } from 'app/shared/model/country-im.model';
import { CountryImService } from './country-im.service';
import { CountryImComponent } from './country-im.component';
import { CountryImDetailComponent } from './country-im-detail.component';
import { CountryImUpdateComponent } from './country-im-update.component';
import { CountryImDeletePopupComponent } from './country-im-delete-dialog.component';
import { ICountryIm } from 'app/shared/model/country-im.model';

@Injectable({ providedIn: 'root' })
export class CountryImResolve implements Resolve<ICountryIm> {
    constructor(private service: CountryImService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((country: HttpResponse<CountryIm>) => country.body));
        }
        return of(new CountryIm());
    }
}

export const countryRoute: Routes = [
    {
        path: 'country-im',
        component: CountryImComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.country.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'country-im/:id/view',
        component: CountryImDetailComponent,
        resolve: {
            country: CountryImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.country.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'country-im/new',
        component: CountryImUpdateComponent,
        resolve: {
            country: CountryImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.country.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'country-im/:id/edit',
        component: CountryImUpdateComponent,
        resolve: {
            country: CountryImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.country.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const countryPopupRoute: Routes = [
    {
        path: 'country-im/:id/delete',
        component: CountryImDeletePopupComponent,
        resolve: {
            country: CountryImResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'inteliManagementApp.country.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
