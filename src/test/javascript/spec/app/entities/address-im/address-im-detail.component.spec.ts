/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { AddressImDetailComponent } from 'app/entities/address-im/address-im-detail.component';
import { AddressIm } from 'app/shared/model/address-im.model';

describe('Component Tests', () => {
    describe('AddressIm Management Detail Component', () => {
        let comp: AddressImDetailComponent;
        let fixture: ComponentFixture<AddressImDetailComponent>;
        const route = ({ data: of({ address: new AddressIm(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [AddressImDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AddressImDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AddressImDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.address).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
