/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InteliManagementTestModule } from '../../../test.module';
import { PhoneImComponent } from 'app/entities/phone-im/phone-im.component';
import { PhoneImService } from 'app/entities/phone-im/phone-im.service';
import { PhoneIm } from 'app/shared/model/phone-im.model';

describe('Component Tests', () => {
    describe('PhoneIm Management Component', () => {
        let comp: PhoneImComponent;
        let fixture: ComponentFixture<PhoneImComponent>;
        let service: PhoneImService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [PhoneImComponent],
                providers: []
            })
                .overrideTemplate(PhoneImComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PhoneImComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PhoneImService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PhoneIm(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.phones[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
