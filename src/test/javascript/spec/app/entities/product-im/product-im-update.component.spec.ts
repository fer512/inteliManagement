/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { ProductImUpdateComponent } from 'app/entities/product-im/product-im-update.component';
import { ProductImService } from 'app/entities/product-im/product-im.service';
import { ProductIm } from 'app/shared/model/product-im.model';

describe('Component Tests', () => {
    describe('ProductIm Management Update Component', () => {
        let comp: ProductImUpdateComponent;
        let fixture: ComponentFixture<ProductImUpdateComponent>;
        let service: ProductImService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ProductImUpdateComponent]
            })
                .overrideTemplate(ProductImUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProductImUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductImService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProductIm(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.product = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProductIm();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.product = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
