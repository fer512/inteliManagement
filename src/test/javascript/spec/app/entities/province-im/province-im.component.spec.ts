/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InteliManagementTestModule } from '../../../test.module';
import { ProvinceImComponent } from 'app/entities/province-im/province-im.component';
import { ProvinceImService } from 'app/entities/province-im/province-im.service';
import { ProvinceIm } from 'app/shared/model/province-im.model';

describe('Component Tests', () => {
    describe('ProvinceIm Management Component', () => {
        let comp: ProvinceImComponent;
        let fixture: ComponentFixture<ProvinceImComponent>;
        let service: ProvinceImService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ProvinceImComponent],
                providers: []
            })
                .overrideTemplate(ProvinceImComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProvinceImComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProvinceImService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ProvinceIm(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.provinces[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
