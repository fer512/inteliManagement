/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InteliManagementTestModule } from '../../../test.module';
import { AddressImComponent } from 'app/entities/address-im/address-im.component';
import { AddressImService } from 'app/entities/address-im/address-im.service';
import { AddressIm } from 'app/shared/model/address-im.model';

describe('Component Tests', () => {
    describe('AddressIm Management Component', () => {
        let comp: AddressImComponent;
        let fixture: ComponentFixture<AddressImComponent>;
        let service: AddressImService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [AddressImComponent],
                providers: []
            })
                .overrideTemplate(AddressImComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AddressImComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AddressImService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AddressIm(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.addresses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
